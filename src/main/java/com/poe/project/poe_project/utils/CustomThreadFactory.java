package com.poe.project.poe_project.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author beck.yang
 * @date 2021/11/22 5:37 下午
 * @description 线程工厂
 */
public class CustomThreadFactory implements ThreadFactory {
	//线程池数量
	private static final AtomicInteger poolNumber = new AtomicInteger(1);
	private final ThreadGroup group;

	//线程数量
	private final AtomicInteger threadNumber = new AtomicInteger(1);
	private final String threadTag;

	public CustomThreadFactory(String threadTag) {
		SecurityManager s = System.getSecurityManager();
		group = (s != null) ? s.getThreadGroup() :
				Thread.currentThread().getThreadGroup();
		this.threadTag = "CMDB-" + poolNumber.getAndIncrement() + "-" + threadTag + "-";
	}

	@Override
	public Thread newThread(Runnable target) {
		Thread t = new Thread(group, target,
				threadTag + threadNumber.getAndIncrement(),
				0);
		if (t.isDaemon()) {
			t.setDaemon(false);
		}
		if (t.getPriority() != Thread.NORM_PRIORITY) {
			t.setPriority(Thread.NORM_PRIORITY);
		}
		return t;
	}
}
