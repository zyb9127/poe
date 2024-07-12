package com.poe.project.poe_project.utils;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author beck.yang
 * @date 2021/11/22 5:37 下午
 * @description 线程池工具类
 */
@Slf4j
@Component
public class ThreadPoolUtil {
	/**
	 * CPU核数
	 */
	private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

	/**
	 * IO密集型线程池
	 */
	public static final int IO_MAX = Math.max(2, CPU_COUNT * 2);

	/**
	 * 混合线程池
	 */
	public static final int MIXED_MAX = IO_MAX * 2;

	/**
	 * 线程空闲时间
	 */
	private static final int KEEP_ALIVE_SECONDS = 30;

	/**
	 * 线程阻塞队列大小
	 */
	public static int QUEUE_SIZE = 128;

	@Getter
	public static int getQueueSize() {
		return QUEUE_SIZE;
	}

	@Setter
	@Value("${thread.pool.queue.size}")
	public void setQueueSize(int queueSize) {
		ThreadPoolUtil.QUEUE_SIZE = queueSize;
	}

	public static int getFixedMax() {
		return MIXED_MAX;
	}

	public static ThreadPoolExecutor getDeleteCiThreadPool() {
		return DeleteCiThreadPool.DELETE_CI_THREAD_POOL;
	}

	private static class DeleteCiThreadPool {
		private static final ThreadPoolExecutor DELETE_CI_THREAD_POOL = new ThreadPoolExecutor(
				IO_MAX,
				IO_MAX,
				KEEP_ALIVE_SECONDS,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(getQueueSize()),
				new CustomThreadFactory("DELETE-CI"));

		static {
			DELETE_CI_THREAD_POOL.allowCoreThreadTimeOut(true);
			//JVM关闭时的钩子函数
			Runtime.getRuntime().addShutdownHook(
					new ShutdownHookThread("DELETE-CI线程池", (Callable<Void>) () -> {
						//优雅关闭线程池
						shutdownThreadPoolGracefully(DELETE_CI_THREAD_POOL);
						return null;
					}));
		}
	}


	/**
	 * 优雅的关闭线程池
	 *
	 * @param threadPool
	 */
	public static void shutdownThreadPoolGracefully(ExecutorService threadPool) {
		if (threadPool == null || threadPool.isTerminated()) {
			return;
		}
		threadPool.shutdown();   //拒绝接受新任务
		try {
			// 等待 60 s，等待线程池中的任务完成执行
			if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
				// 调用 shutdownNow 取消正在执行的任务
				threadPool.shutdownNow();
				// 再次等待 60 s，如果还未结束，可以再次尝试，或则直接放弃
				if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
					log.debug("线程池任务未正常执行结束");
				}
			}
		} catch (InterruptedException ie) {
			// 捕获异常，重新调用 shutdownNow
			threadPool.shutdownNow();
			Thread.currentThread().interrupt();
		}
		// 再次检查是否成功关闭，最多尝试关闭 1000 次，每次等待 10 毫秒
		if (!threadPool.isTerminated()) {
			int attempts = 0;
			boolean isTerminated = false;
			while (attempts < 1000 && !isTerminated) {
				threadPool.shutdownNow();
				attempts++;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					log.error("Thread pool shutdown error", e);
					Thread.currentThread().interrupt();
				}
				isTerminated = threadPool.isTerminated(); // 检查线程池是否已经成功关闭
			}
		}
	}
}
