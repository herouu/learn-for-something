package com.github.concurrent.addition;

import lombok.SneakyThrows;

import static com.github.concurrent.addition.AddConstant.THREAD_NUM;
import static com.github.concurrent.addition.NormalAdd.Sum;
import static com.github.concurrent.addition.ThreadPoolAdd.multiSum;

public class AddMain {

    @SneakyThrows
    public static void main(String[] args) {
        //实现多线程加法求和
        // Sum(AddConstant.N);
        // threadPool
        // multiSum(AddConstant.N, THREAD_NUM);
        // CountDownLatch
        CountDownLatchAdd.countDownLatchSum(AddConstant.N, THREAD_NUM);
        // cyclicBarrierSum
        CyclicBarrierAdd.cyclicBarrierSum(AddConstant.N, THREAD_NUM);
        // todo:cyclicBarrierSum2  此方法会造成死锁，原因不详，需要进行排查
        // CyclicBarrierAdd.cyclicBarrierSum2(AddConstant.N, THREAD_NUM);
        // forkJoin
        // ForkJoinAdd.forkJoinSum(AddConstant.N, THREAD_NUM, 10000);
        // java8并行流
        ParallelStreamAdd.parallelSum(AddConstant.N);
    }
}
