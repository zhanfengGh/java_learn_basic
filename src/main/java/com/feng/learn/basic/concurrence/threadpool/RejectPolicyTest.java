package com.feng.learn.basic.concurrence.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RejectPolicyTest {

    public static void main(String[] args) {
        final AtomicInteger count = new AtomicInteger(0);
        /**如果keepAliveTime>0，则时间一到所有的线程都被回收了，包括corePoolSize的线程;
         * 若keepAliveTime=0， corePoolSize的线程保留在线程池内。
         * 与设计不符
         */
        ExecutorService executor = new ThreadPoolExecutor(2, 8, 2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        Runnable task = new Runnable() {

            public void run() {
                int temp = count.addAndGet(1);
                try {
                    Thread.sleep(10*60*1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(temp);
                System.out.println();
            }

        };

        for (int i = 0; i < 15; i++) {
            executor.submit(task);
        }
    }

}
