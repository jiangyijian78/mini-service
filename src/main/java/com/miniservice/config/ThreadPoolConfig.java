package com.miniservice.config;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

@EnableAsync
@Configuration
public class ThreadPoolConfig {

	Logger logger = LoggerFactory.getLogger(ThreadPoolConfig.class);

	static final int CORE_POOL_SIZE = 5;// 处于空闲状态也要保留在池中的线程数
	static final int MAXIMUM_THREAD_NUMBER = 20;// 池中允许的最大线程数
	static final long KEEP_ALIVE_TIME = 60L;// 当线程数大于内核数时，这是多余的空闲线程将在终止之前等待新任务的最长时间。
	static final int WORK_QUEUE_SIZE = 1024;// 队列容量

	final ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("async-task-name-%d").setDaemon(true)
			.build();

	@Bean("taskExecutor")
	public Executor taskExecutor() {
		return new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_THREAD_NUMBER, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(WORK_QUEUE_SIZE), threadFactory, (r, executor) -> {
					// 打印日志， 添加监控等
					logger.info("task is rejected!");
				});

	}
}
