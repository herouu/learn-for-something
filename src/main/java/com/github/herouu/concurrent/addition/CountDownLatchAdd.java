package com.github.herouu.concurrent.addition;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchAdd {


    public static class SumCThread implements Runnable {

        private long start;
        private long end;
        private long[] result;
        private CountDownLatch cdl;
        private int num;

        public SumCThread(CountDownLatch cdl, long[] result, long start, long end,
                          int num) {
            this.result = result;
            this.start = start;
            this.end = end;
            this.cdl = cdl;
            this.num = num;
        }

        @Override
        public void run() {
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            result[num] = sum;
            cdl.countDown();
        }
    }


    //每个线程结果怎么返回  线程如何等待最终求值
    //使用CountDownLatch
    public static void countDownLatchSum(long N, int numThread) throws
            InterruptedException {
        System.out.println("CountDownLatch------------------------");
        long start1 = System.currentTimeMillis();
        CountDownLatch cdl = new CountDownLatch(numThread);
        long[] result = new long[numThread];
        long sum = 0L;
        for (int i = 0; i < numThread; i++) {
            new Thread(
                    new SumCThread(cdl, result, i * N / numThread + 1, (i + 1) * N / numThread, i))
                    .start();
        }
        cdl.await();
        for (int i = 0; i < numThread; i++) {
            sum += result[i];
        }
        //并行计算
        long end1 = System.currentTimeMillis();
        System.out.println("CountDownLatch耗时：" + (end1 - start1) + " ms");
        System.out.println("CountDownLatch结果：" + sum);
    }
}
