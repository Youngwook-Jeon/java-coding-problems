package com.project.young.firstedition.chap11;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Logger;

public class P214_RecursiveTaskSum {
    private static final Logger logger = Logger.getLogger(P214_RecursiveTaskSum.class.getName());

    public static class SumRecursiveTask extends RecursiveTask<Integer> {

        private static final int THRESHOLD = 10;
        private final List<Integer> workList;

        public SumRecursiveTask(List<Integer> workList) {
            this.workList = workList;
        }

        @Override
        protected Integer compute() {
            if (workList.size() <= THRESHOLD) return partialSum(workList);

            return ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .mapToInt(ForkJoinTask::join)
                    .sum();
        }

        private List<SumRecursiveTask> createSubtasks() {
            List<SumRecursiveTask> subTasks = new ArrayList<>();
            int size = workList.size();

            List<Integer> workListLeft = workList.subList(0, (size + 1) / 2);
            List<Integer> workListRight = workList.subList((size + 1) / 2, size);

            subTasks.add(new SumRecursiveTask(workListLeft));
            subTasks.add(new SumRecursiveTask(workListRight));

            return subTasks;
        }

        private Integer partialSum(List<Integer> workList) {
            int sum = workList.stream()
                    .mapToInt(e -> e)
                    .sum();
            logger.info(() -> STR."Partial sum: \{workList} = \{sum}\tThread: \{Thread.currentThread().getName()}");

            return sum;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        Random rnd = new Random();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 200; i++) {
            list.add(1 + rnd.nextInt(10));
        }

        SumRecursiveTask sumRecursiveTask = new SumRecursiveTask(list);
        Integer sumAll = forkJoinPool.invoke(sumRecursiveTask);

        logger.info(() -> STR."Final sum: \{sumAll}");
    }
}
