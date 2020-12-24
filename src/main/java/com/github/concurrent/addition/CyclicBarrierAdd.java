package com.github.concurrent.addition;

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
        long start1 = System.currentTimeMillis();
        CyclicBarrier cb = new CyclicBarrier(numThread + 1);
        long[] result = new long[numThread];
        long sum = 0L;
        for (int i = 0; i < numThread; i++) {
            new Thread(
                    new SumCBThread(cb, result, i * N / numThread + 1, (i + 1) * N / numThread, i))
                    .start();
        }
        cb.await();
        for (int i = 0; i < numThread; i++) {
            sum += result[i];
        }
        //并行计算
        long end1 = System.currentTimeMillis();
        System.out.println("cyclicBarrier耗时：" + (end1 - start1) + " ms");
        System.out.println("cyclicBarrier结果：" + sum);
    }


    public static class SumCB implements Runnable {

        long[] result;

        public SumCB(long[] result) {
            this.result = result;
        }

        @Override
        public void run() {
            long sum = 0L;
            for (int i = 0; i < result.length; i++) {
                sum += result[i];
            }
            System.out.println("cyclicBarrierSum2结果：" + sum);
        }
    }

    public static void cyclicBarrierSum2(long N, int numThread) {
        System.out.println("使用CycleBarrier构造器--------------------------------");
        long start1 = System.currentTimeMillis();
        long[] result = new long[numThread];
        CyclicBarrier cb = new CyclicBarrier(numThread + 1, new SumCB(result));
        for (int i = 0; i < numThread; i++) {
            new Thread(new SumCBThread(cb, result, i * N / numThread + 1, (i + 1) * N / numThread, i))
                    .start();
        }
        //并行计算
        long end1 = System.currentTimeMillis();
        System.out.println("cyclicBarrierSum2计算耗时：" + (end1 - start1) + " ms");
    }
}
