package com.project.young.chap11.P225_SimpleVirtualThreadContinuations;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class Solution {

    private static final Logger logger = Logger.getLogger(Solution.class.getName());

    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tT] [%4$-7s] %5$s %n");

        List<Thread> vThreads = IntStream.range(0, 5)
                .mapToObj(i -> Thread.ofVirtual().unstarted(() -> {
                    if (i == 0) logger.info(Thread.currentThread().toString()); // VirtualThread[#30]/runnable@ForkJoinPool-1-worker-1

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {

                    }

                    if (i == 0) logger.info(Thread.currentThread().toString()); // VirtualThread[#30]/runnable@ForkJoinPool-1-worker-3
                })).toList();

        vThreads.forEach(Thread::start);
        vThreads.forEach(thread -> {
            try { thread.join(); } catch (InterruptedException ex) {}
        });
    }
}
