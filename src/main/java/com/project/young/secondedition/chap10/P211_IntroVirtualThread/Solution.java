package com.project.young.secondedition.chap10.P211_IntroVirtualThread;

import java.util.concurrent.ThreadFactory;
import java.util.logging.Logger;

public class Solution {
    private static final Logger logger = Logger.getLogger(Solution.class.getName());

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tT] [%4$-7s] %5$s %n");
        Runnable task = () -> logger.info(Thread.currentThread().toString());

        Thread vThread = Thread.ofVirtual().name("my_vThread").unstarted(task);

        logger.info("Is daemon? " + vThread.isDaemon());
        logger.info("Priority? " + vThread.getPriority()); // 5
        vThread.setPriority(4);
        logger.info("Priority? " + vThread.getPriority());

        logger.info("Before running the task...");
        Thread vThread2 = Thread.startVirtualThread(task);
        logger.info("While running the task...");
        vThread2.join();
        logger.info("After running the task...");

        Thread.Builder builder = Thread.ofVirtual().name("vThread-", 1);
        Thread vThread3 = builder.start(task);
        vThread3.join();

        ThreadFactory tf = Thread.ofVirtual().name("vt-", 0).factory();
        SimpleThreadFactory stf = new SimpleThreadFactory();
        tf.newThread(task).start();
        stf.newThread(task).start();
    }

    static class SimpleThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            return Thread.ofVirtual().unstarted(r);
        }
    }
}
