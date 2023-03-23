package com.poe.project.poe_project.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author beck.yang
 * @date 2021/11/22 5:37 下午
 * @description 线程关闭
 */
@Slf4j
public class ShutdownHookThread extends Thread {
	private volatile boolean hasShutdown = false;
	private static AtomicInteger shutdownTimes = new AtomicInteger(0);
	private final Callable callback;

	public ShutdownHookThread(String name, Callable callback) {
		super("JVM退出钩子(" + name + ")");

		this.callback = callback;
	}

	@Override
	public void run() {
		synchronized (this) {
			log.info(getName() + " starting.... ");
			if (!this.hasShutdown) {
				this.hasShutdown = true;
				long beginTime = System.currentTimeMillis();
				try {
					this.callback.call();
				} catch (Exception e) {
					log.error(getName() + " error: " + e.getMessage());
				}
				long consumingTimeTotal = System.currentTimeMillis() - beginTime;
				log.info(getName() + "  耗时(ms): " + consumingTimeTotal);
			}
		}
	}
}