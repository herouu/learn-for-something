package com.github.herouu.concurrent.addition;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

public class ParallelStreamAdd {


    public static void parallelSum(long max) {
        System.out.println("java8 并行流计算------------------------");
        Instant start = Instant.now();
        LongStream longStream = LongStream.rangeClosed(1, max);
        long result = longStream.parallel().sum();
        Instant end = Instant.now();
        System.out.println("并行流计算结果：" + result);
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");
    }
}
