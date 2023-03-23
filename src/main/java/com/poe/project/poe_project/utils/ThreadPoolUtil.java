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

	public static ThreadPoolExecutor getCreateCiThreadPool() {
		return CreateCiThreadPool.CREATE_CI_THREAD_POOL;
	}

	public static ThreadPoolExecutor getSyncCiThreadPool() {
		return SyncCiThreadPool.SYNC_CI_THREAD_POOL;
	}

	public static ThreadPoolExecutor getUpdateCiThreadPool() {
		return UpdateCiThreadPool.UPDATE_CI_THREAD_POOL;
	}

	public static ThreadPoolExecutor getUpsetCiThreadPool() {
		return UpsetCiThreadPool.UPSET_CI_THREAD_POOL;
	}

	public static ThreadPoolExecutor getDeleteCiThreadPool() {
		return DeleteCiThreadPool.DELETE_CI_THREAD_POOL;
	}

	public static ThreadPoolExecutor getSendNoticeThreadPool() {
		return SendNoticeThreadPool.SEND_NOTICE_THREAD_POOL;
	}

	public static ThreadPoolExecutor getSaveVersionThreadPool() {
		return CiVersionThreadPool.SAVE_CI_VERSION_THREAD_POOL;
	}

	public static ThreadPoolExecutor getSendEventThreadPool() {
		return SendEventThreadPool.SEND_EVENT_THREAD_POOL;
	}

	public static ThreadPoolExecutor getChangLogThreadPool() {
		return ChangLogThreadPool.CHANGE_LOG_THREAD_POOL;
	}

	public static ThreadPoolExecutor getAuditLogThreadPool() {
		return AuditLogThreadPool.AUDIT_LOG_THREAD_POOL;
	}

	public static ThreadPoolExecutor getDelayQueueThreadPool() {
		return DelayQueueThreadPool.DELAY_QUEUE_THREAD_POOL;
	}

	public static ThreadPoolExecutor getUpdateModelsByDictThreadPool() {
		return DelayQueueThreadPool.DELAY_QUEUE_THREAD_POOL;
	}

	public static ThreadPoolExecutor getCiTopologyRelationsThreadPool() {
		return CiTopologyRelationsThreadPool.CI_TOPOLOGY_RELATIONS_THREAD_POOL;
	}


	private static class UpdateModelsDictThreadPool {
		private static final ThreadPoolExecutor UPDATE_MODELS_BY_DICT_THREADPOOL = new ThreadPoolExecutor(
				IO_MAX,
				IO_MAX,
				KEEP_ALIVE_SECONDS,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue(getQueueSize()),
				new CustomThreadFactory("DICT-AUTO"));

		static {
			UPDATE_MODELS_BY_DICT_THREADPOOL.allowCoreThreadTimeOut(true);
			//JVM关闭时的钩子函数
			Runtime.getRuntime().addShutdownHook(
					new ShutdownHookThread("DICT-AUTO线程池", new Callable<Void>() {
						@Override
						public Void call() throws Exception {
							//优雅关闭线程池
							shutdownThreadPoolGracefully(UPDATE_MODELS_BY_DICT_THREADPOOL);
							return null;
						}
					}));
		}
	}



	private static class DelayQueueThreadPool {
		private static final ThreadPoolExecutor DELAY_QUEUE_THREAD_POOL = new ThreadPoolExecutor(
				IO_MAX,
				IO_MAX,
				KEEP_ALIVE_SECONDS,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue(getQueueSize()),
				new CustomThreadFactory("DELAY-QUEUE"));

		static {
			DELAY_QUEUE_THREAD_POOL.allowCoreThreadTimeOut(true);
			//JVM关闭时的钩子函数
			Runtime.getRuntime().addShutdownHook(
					new ShutdownHookThread("DELAY_QUEUE线程池", new Callable<Void>() {
						@Override
						public Void call() throws Exception {
							//优雅关闭线程池
							shutdownThreadPoolGracefully(DELAY_QUEUE_THREAD_POOL);
							return null;
						}
					}));
		}
	}


	public static ThreadPoolExecutor getCiWorkerThreadPool() {
		return CiWorkerThreadPool.PROCESSOR_CI_WORKER_THREAD_POOL;
	}

	public static ThreadPoolExecutor getRelationWorkerThreadPool() {
		return RelationWorkerThreadPool.PROCESSOR_RELATION_WORKER_THREAD_POOL;
	}

	private static class CiWorkerThreadPool {
		private static final ThreadPoolExecutor PROCESSOR_CI_WORKER_THREAD_POOL = new ThreadPoolExecutor(
				MIXED_MAX,
				MIXED_MAX,
				KEEP_ALIVE_SECONDS,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue(getQueueSize()),
				new CustomThreadFactory("PROCESSOR-CI-WORKER"));

		static {
			PROCESSOR_CI_WORKER_THREAD_POOL.allowCoreThreadTimeOut(true);
			//JVM关闭时的钩子函数
			// TODO processor worker关闭后无法从redisson获取队列数据
//			Runtime.getRuntime().addShutdownHook(
//					new ShutdownHookThread("PROCESSOR-CI-WORKER线程池", new Callable<Void>() {
//						@Override
//						public Void call() throws Exception {
//							//优雅关闭线程池
//							shutdownThreadPoolGracefully(PROCESSOR_CI_WORKER_THREAD_POOL);
//							return null;
//						}
//					}));
		}
	}

	private static class UpsetCiThreadPool {
		private static final ThreadPoolExecutor UPSET_CI_THREAD_POOL = new ThreadPoolExecutor(
				IO_MAX,
				MIXED_MAX,
				3,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue(getQueueSize()),
				new CustomThreadFactory("UPSET-CI"));

		static {
			UPSET_CI_THREAD_POOL.allowCoreThreadTimeOut(true);
//			UPSET_CI_THREAD_POOL.setRejectedExecutionHandler(rejectedExecutionHandler);
			//JVM关闭时的钩子函数
			Runtime.getRuntime().addShutdownHook(
					new ShutdownHookThread("UPSET-CI线程池", new Callable<Void>() {
						@Override
						public Void call() throws Exception {
							//优雅关闭线程池
							shutdownThreadPoolGracefully(UPSET_CI_THREAD_POOL);
							return null;
						}
					}));
		}
	}

	private static class RelationWorkerThreadPool {
		private static final ThreadPoolExecutor PROCESSOR_RELATION_WORKER_THREAD_POOL = new ThreadPoolExecutor(
				MIXED_MAX,
				MIXED_MAX,
				KEEP_ALIVE_SECONDS,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue(getQueueSize()),
				new CustomThreadFactory("PROCESSOR-RELATION-WORKER"));

		static {
			PROCESSOR_RELATION_WORKER_THREAD_POOL.allowCoreThreadTimeOut(true);
			//JVM关闭时的钩子函数
//			Runtime.getRuntime().addShutdownHook(
//					new ShutdownHookThread("PROCESSOR-RELATION-WORKER线程池", new Callable<Void>() {
//						@Override
//						public Void call() throws Exception {
//							//优雅关闭线程池
//							shutdownThreadPoolGracefully(PROCESSOR_RELATION_WORKER_THREAD_POOL);
//							return null;
//						}
//					}));
		}
	}

	private static class AuditLogThreadPool {
		private static final ThreadPoolExecutor AUDIT_LOG_THREAD_POOL = new ThreadPoolExecutor(
				IO_MAX,
				IO_MAX,
				KEEP_ALIVE_SECONDS,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue(getQueueSize()),
				new CustomThreadFactory("AUDIT-LOG"));

		static {
			AUDIT_LOG_THREAD_POOL.allowCoreThreadTimeOut(true);
			//JVM关闭时的钩子函数
			Runtime.getRuntime().addShutdownHook(
					new ShutdownHookThread("AUDIT-LOG线程池", new Callable<Void>() {
						@Override
						public Void call() throws Exception {
							//优雅关闭线程池
							shutdownThreadPoolGracefully(AUDIT_LOG_THREAD_POOL);
							return null;
						}
					}));
		}
	}

	private static class CiVersionThreadPool {
		private static final ThreadPoolExecutor SAVE_CI_VERSION_THREAD_POOL = new ThreadPoolExecutor(
				IO_MAX,
				IO_MAX,
				KEEP_ALIVE_SECONDS,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue(getQueueSize()),
				new CustomThreadFactory("SAVE-CI-VERSION"));

		static {
			SAVE_CI_VERSION_THREAD_POOL.allowCoreThreadTimeOut(true);
			//JVM关闭时的钩子函数
			Runtime.getRuntime().addShutdownHook(
					new ShutdownHookThread("SAVE-CI-VERSIONE线程池", new Callable<Void>() {
						@Override
						public Void call() throws Exception {
							//优雅关闭线程池
							shutdownThreadPoolGracefully(SAVE_CI_VERSION_THREAD_POOL);
							return null;
						}
					}));
		}
	}

	private static class ChangLogThreadPool {
		private static final ThreadPoolExecutor CHANGE_LOG_THREAD_POOL = new ThreadPoolExecutor(
				IO_MAX,
				IO_MAX,
				KEEP_ALIVE_SECONDS,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue(getQueueSize()),
				new CustomThreadFactory("CHANGE-LOG"));

		static {
			CHANGE_LOG_THREAD_POOL.allowCoreThreadTimeOut(true);
			//JVM关闭时的钩子函数
			Runtime.getRuntime().addShutdownHook(
					new ShutdownHookThread("CHANGE-LOG线程池", new Callable<Void>() {
						@Override
						public Void call() throws Exception {
							//优雅关闭线程池
							shutdownThreadPoolGracefully(CHANGE_LOG_THREAD_POOL);
							return null;
						}
					}));
		}
	}

	private static class SendNoticeThreadPool {
		private static final ThreadPoolExecutor SEND_NOTICE_THREAD_POOL = new ThreadPoolExecutor(
				IO_MAX,
				IO_MAX,
				KEEP_ALIVE_SECONDS,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue(getQueueSize()),
				new CustomThreadFactory("SEND-NOTICE"));

		static {
			SEND_NOTICE_THREAD_POOL.allowCoreThreadTimeOut(true);
			//JVM关闭时的钩子函数
			Runtime.getRuntime().addShutdownHook(
					new ShutdownHookThread("SEND-NOTICE线程池", new Callable<Void>() {
						@Override
						public Void call() throws Exception {
							//优雅关闭线程池
							shutdownThreadPoolGracefully(SEND_NOTICE_THREAD_POOL);
							return null;
						}
					}));
		}
	}

	private static class CreateCiThreadPool {
		private static final ThreadPoolExecutor CREATE_CI_THREAD_POOL = new ThreadPoolExecutor(
				IO_MAX,
				IO_MAX,
				KEEP_ALIVE_SECONDS,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue(getQueueSize()),
				new CustomThreadFactory("CREATE-CI"));

		static {
			CREATE_CI_THREAD_POOL.allowCoreThreadTimeOut(true);
			//JVM关闭时的钩子函数
			Runtime.getRuntime().addShutdownHook(
					new ShutdownHookThread("CREATE-CI线程池", new Callable<Void>() {
						@Override
						public Void call() throws Exception {
							//优雅关闭线程池
							shutdownThreadPoolGracefully(CREATE_CI_THREAD_POOL);
							return null;
						}
					}));
		}
	}


	private static class SyncCiThreadPool {
		private static final ThreadPoolExecutor SYNC_CI_THREAD_POOL = new ThreadPoolExecutor(
				IO_MAX,
				IO_MAX,
				KEEP_ALIVE_SECONDS,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue(getQueueSize()),
				new CustomThreadFactory("SYNC-CI"));

		static {
			SYNC_CI_THREAD_POOL.allowCoreThreadTimeOut(true);
			//JVM关闭时的钩子函数
			Runtime.getRuntime().addShutdownHook(
					new ShutdownHookThread("SYNC-CI线程池", new Callable<Void>() {
						@Override
						public Void call() throws Exception {
							//优雅关闭线程池
							shutdownThreadPoolGracefully(SYNC_CI_THREAD_POOL);
							return null;
						}
					}));
		}
	}

	private static class UpdateCiThreadPool {
		private static final ThreadPoolExecutor UPDATE_CI_THREAD_POOL = new ThreadPoolExecutor(
				IO_MAX,
				IO_MAX,
				KEEP_ALIVE_SECONDS,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue(getQueueSize()),
				new CustomThreadFactory("UPDATE-CI"));

		static {
			UPDATE_CI_THREAD_POOL.allowCoreThreadTimeOut(true);
			//JVM关闭时的钩子函数
			Runtime.getRuntime().addShutdownHook(
					new ShutdownHookThread("UPDATE-CI线程池", new Callable<Void>() {
						@Override
						public Void call() throws Exception {
							//优雅关闭线程池
							shutdownThreadPoolGracefully(UPDATE_CI_THREAD_POOL);
							return null;
						}
					}));
		}
	}

	private static class DeleteCiThreadPool {
		private static final ThreadPoolExecutor DELETE_CI_THREAD_POOL = new ThreadPoolExecutor(
				IO_MAX,
				IO_MAX,
				KEEP_ALIVE_SECONDS,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue(getQueueSize()),
				new CustomThreadFactory("DELETE-CI"));

		static {
			DELETE_CI_THREAD_POOL.allowCoreThreadTimeOut(true);
			//JVM关闭时的钩子函数
			Runtime.getRuntime().addShutdownHook(
					new ShutdownHookThread("DELETE-CI线程池", new Callable<Void>() {
						@Override
						public Void call() throws Exception {
							//优雅关闭线程池
							shutdownThreadPoolGracefully(DELETE_CI_THREAD_POOL);
							return null;
						}
					}));
		}
	}

	private static class SendEventThreadPool {
		private static final ThreadPoolExecutor SEND_EVENT_THREAD_POOL = new ThreadPoolExecutor(
				IO_MAX,
				IO_MAX,
				KEEP_ALIVE_SECONDS,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue(getQueueSize()),
				new CustomThreadFactory("SEND-EVENT"));

		static {
			SEND_EVENT_THREAD_POOL.allowCoreThreadTimeOut(true);
			//JVM关闭时的钩子函数
			Runtime.getRuntime().addShutdownHook(
					new ShutdownHookThread("SEND-EVENT线程池", new Callable<Void>() {
						@Override
						public Void call() throws Exception {
							//优雅关闭线程池
							shutdownThreadPoolGracefully(SEND_EVENT_THREAD_POOL);
							return null;
						}
					}));
		}
	}

	/**
	 * 优雅的关闭线程池
	 *
	 * @param threadPool
	 */
	public static void shutdownThreadPoolGracefully(ExecutorService threadPool) {
		if (!(threadPool instanceof ExecutorService) || threadPool.isTerminated()) {
			return;
		}
		try {
			threadPool.shutdown();   //拒绝接受新任务
		} catch (SecurityException e) {
			return;
		} catch (NullPointerException e) {
			return;
		}
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
		//任然没有关闭，循环关闭1000次，每次等待10毫秒
		if (!threadPool.isTerminated()) {
			try {
				for (int i = 0; i < 1000; i++) {
					if (threadPool.awaitTermination(10, TimeUnit.MILLISECONDS)) {
						break;
					}
					threadPool.shutdownNow();
				}
			} catch (Throwable e) {
				log.error("Thread pool shutdown error", e);
				Thread.currentThread().interrupt();
			}
		}
	}


	private static class CiTopologyRelationsThreadPool {
		private static final ThreadPoolExecutor CI_TOPOLOGY_RELATIONS_THREAD_POOL = new ThreadPoolExecutor(
				IO_MAX,
				IO_MAX,
				KEEP_ALIVE_SECONDS,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue(getQueueSize()),
				new CustomThreadFactory("CI-TOPOLOGY-AUTO"));

		static {
			CI_TOPOLOGY_RELATIONS_THREAD_POOL.allowCoreThreadTimeOut(true);
			//JVM关闭时的钩子函数
			Runtime.getRuntime().addShutdownHook(
					new ShutdownHookThread("CI-TOPOLOGY-AUTO线程池", new Callable<Void>() {
						@Override
						public Void call() throws Exception {
							//优雅关闭线程池
							shutdownThreadPoolGracefully(CI_TOPOLOGY_RELATIONS_THREAD_POOL);
							return null;
						}
					}));
		}
	}
}
