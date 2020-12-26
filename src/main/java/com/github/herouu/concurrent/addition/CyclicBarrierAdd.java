package com.github.herouu.concurrent.addition;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierAdd {


    public static class SumCBThread implements Runnable {

        private long start;
        private long end;
        private long[] result;
        private CyclicBarrier cb;
        private int num;

        public SumCBThread(CyclicBarrier cb, long[] result, long start, long end,
                           int num) {
            this.result = result;
            this.start = start;
            this.end = end;
            this.cb = cb;
            this.num = num;
        }

        @Override
        public void run() {
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            result[num] = sum;
            try {
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    //使用同步屏障来实现
    public static void cyclicBarrierSum(long N, int numThread) throws
            BrokenBarrierException, InterruptedException {
        System.out.println("cyclicBarrier-------------------------------------");
        Instant start = Instant.now();
        // 在主线程进行汇总，所以+1，使用public CyclicBarrier(int parties) 构造器
        CyclicBarrier cb = new CyclicBarrier(numThread + 1);
        long[] result = new long[numThread];
        long sum = 0L;
        for (int i = 0; i < numThread; i++) {
            new Thread(
                    new SumCBThread(cb, result, i * N / numThread + 1, (i + 1) * N / numThread, i))
                    .start();
        }
        // 主线程等待所有线程到达屏障后执行方法
        cb.await();
        for (int i = 0; i < numThread; i++) {
            sum += result[i];
        }
        //并行计算
        Instant end = Instant.now();
        System.out.println("cyclicBarrier耗时：" + Duration.between(start, end).toMillis() + " ms");
        System.out.println("cyclicBarrier结果：" + sum);
    }

    public static class SumCB implements Runnable {

        long[] result;

        Instant start;

        public SumCB(long[] result, Instant start) {
            this.result = result;
            this.start = start;
        }

        @Override
        public void run() {
            long sum = 0L;
            for (int i = 0; i < result.length; i++) {
                sum += result[i];
            }
            Instant end = Instant.now();
            System.out.println("cyclicBarrierSum2耗时：" + Duration.between(start, end).toMillis() + "ms");
            System.out.println("cyclicBarrierSum2结果：" + sum);
        }
    }

    public static void cyclicBarrierSum2(long N, int numThread) {
        System.out.println("使用CycleBarrier构造器--------------------------------");
        Instant start = Instant.now();
        long[] result = new long[numThread];
        //使用CyclicBarrier(int parties, Runnable barrierAction)构造器，当最后一个线程到达屏障，执行run中方法
        CyclicBarrier cb = new CyclicBarrier(numThread, new SumCB(result, start));
        for (int i = 0; i < numThread; i++) {
            Thread thread = new Thread(new SumCBThread(cb, result, i * N / numThread + 1, (i + 1) * N / numThread, i));
            thread.start();
        }
    }
}
