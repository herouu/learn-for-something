package com.github.herouu.concurrent.addition;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinAdd {


    public static void forkJoinSum(long N, Integer thread, Integer taskHold) {
        System.out.println("forkJoin求和--------------------------");
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool(thread);
        ForkJoinTask<Long> task = new ForkJoinCalculate(1, N, taskHold);
        Long sum = pool.invoke(task);
        Instant end = Instant.now();
        System.out.println("forkJoin结果：" + sum);
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");
    }

    public static class ForkJoinCalculate extends RecursiveTask<Long> {

        private long start;
        private long end;

        private Integer taskHold;

        public ForkJoinCalculate(long start, long end, Integer taskHold) {
            this.start = start;
            this.end = end;
            this.taskHold = taskHold;
        }

        @Override
        protected Long compute() {
            long length = end - start;
            if (length <= taskHold) {
                long sum = 0;
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            } else {
                long middle = (start + end) >> 1;
                ForkJoinCalculate left = new ForkJoinCalculate(start, middle, taskHold);
                ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end, taskHold);
                invokeAll(left, right);
                return left.join() + right.join();
            }
        }
    }
}
