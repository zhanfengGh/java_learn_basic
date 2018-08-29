/**
 * @(#)EmailThreadPool.java   2016-02-26
 * Copyright 2012  it.kedacom.com, Inc. All rights reserved.
 */

package com.feng.learn.basic.concurrence.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池，管理邮件发送线程
 * 
 * @author zhangzhanfeng
 * @date 2016-02-26
 */
public class EmailThreadPool {
	
	public static enum TaskType{
		URGENT,NORMAL
	}
	
	private static final int THREAD_POOL_NUMBER=4;
	
	/** cachedThreadPool */
	private final ExecutorService cachedThreadPool=Executors.newCachedThreadPool(new DefaultThreadFactory("cachedThreadPool"));
	
	/** fixedThreadPool */
	private final ExecutorService fixedThreadPool=Executors.newFixedThreadPool(THREAD_POOL_NUMBER, new DefaultThreadFactory("fixedThreadPool"));
	
	/**
	 * 提交紧急任务或一般任务
	 * 
	 * @param task 需要提交的任务
	 * @param taskType EmailThreadPool.TaskType.URGENT(紧急任务，需要立即被线程池执行) OR EmailThreadPool.TaskType.NORMAL (一般任务) 
	 * @return
	 */
	public Future<?> submit(Runnable task, TaskType taskType){
		if (taskType== TaskType.URGENT){
			return cachedThreadPool.submit(task);
		}
		return fixedThreadPool.submit(task);
	}
	
	/**
	 * 提交一般任务
	 * 
	 * @param task
	 * @return
	 */
	public Future<?> submit(Runnable task){
		return fixedThreadPool.submit(task);
	}
	
	


	/**
     * The default thread factory
     */
    static class DefaultThreadFactory implements ThreadFactory {
        final ThreadGroup group;
        final AtomicInteger threadNumber = new AtomicInteger(1);
        final String namePrefix;

        DefaultThreadFactory(String type) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null)? s.getThreadGroup() :
                                 Thread.currentThread().getThreadGroup();
            namePrefix = "Email-"+type+"-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                                  namePrefix + threadNumber.getAndIncrement(),
                                  0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
