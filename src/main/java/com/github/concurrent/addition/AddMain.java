package com.github.concurrent.addition;

import lombok.SneakyThrows;

import static com.github.concurrent.addition.AddConstant.THREAD_NUM;
import static com.github.concurrent.addition.NormalAdd.Sum;
import static com.github.concurrent.addition.ThreadPoolAdd.multiSum;

public class AddMain {

    @SneakyThrows
    public static void main(String[] args) {
        //实现多线程加法求和
        Sum(AddConstant.N);
        // threadPool ok
        multiSum(AddConstant.N, THREAD_NUM);
        // CountDownLatch ok
        CountDownLatchAdd.countDownLatchSum(AddConstant.N, THREAD_NUM);
        // cyclicBarrierSum ok
        CyclicBarrierAdd.cyclicBarrierSum(AddConstant.N, THREAD_NUM);
        // cyclicBarrierSum2 ok
        CyclicBarrierAdd.cyclicBarrierSum2(AddConstant.N, THREAD_NUM);
        // forkJoin ok
        ForkJoinAdd.forkJoinSum(AddConstant.N, THREAD_NUM, 10000);
        // java8并行流 ok
        ParallelStreamAdd.parallelSum(AddConstant.N);
    }
}
