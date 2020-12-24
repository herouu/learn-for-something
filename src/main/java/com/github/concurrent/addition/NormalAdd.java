package com.github.concurrent.addition;

import java.time.Duration;
import java.time.Instant;

public class NormalAdd {

    public static void Sum(long N) {
        System.out.println("串行计算开始-----------------------");
        Instant start = Instant.now();
        long sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += i;
        }
        Instant end = Instant.now();
        System.out.println("串行计算耗时：" + Duration.between(start, end).toMillis() + " ms");
        System.out.println("串行计算的结果：" + sum);
    }
}
