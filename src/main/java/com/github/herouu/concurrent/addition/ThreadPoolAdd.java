package com.github.herouu.concurrent.addition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolAdd {


    public static class SumThread implements Callable<Long> {

        private long start;
        private long end;

        public SumThread(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Long call() throws Exception {
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }
    }

    //使用线程池+Callable接口
    public static void multiSum(long N, int numThread) throws ExecutionException,
            InterruptedException {
        System.out.println("线程池计算开始-----------------------");
        ExecutorService executor = Executors.newFixedThreadPool(numThread);
        long start1 = System.currentTimeMillis();
        List<Future<Long>> ans = new ArrayList<>();
        for (int i = 0; i < numThread; i++) {
            Future<Long> a = executor.submit(
                    // 计算1-50，51-100的和
                    new SumThread(i * N / numThread + 1, (i + 1) * N / numThread));
            ans.add(a);
        }
        long sum = 0;
        for (Future<Long> i : ans) {
            long tmp = i.get();
            System.out.println("线程 " + i + " 的结果是: " + tmp);
            sum += tmp;
        }
        //并行计算
        long end1 = System.currentTimeMillis();
        System.out.println("并行计算耗时：" + (end1 - start1) + " ms");
        System.out.println("并行计算的结果：" + sum);
        executor.shutdown();
    }
}
