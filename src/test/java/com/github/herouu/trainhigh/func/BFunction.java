package com.github.herouu.trainhigh.func;

import java.util.function.Function;

public class BFunction {
    public static void main(String[] args) {
        BFunction bFunction = new BFunction();
        Integer add = bFunction.add(123, item -> {
            System.out.println(item);
            return BFunction.num(item);
        });
        Integer add1 = bFunction.add(100, BFunction::build);
        System.out.println(add1);
        System.out.println(add);
    }

    private static Integer num(Integer o) {
        return o * o;
    }

    private static Integer build(Integer num) {
        return num * num * num;
    }

    Integer add(Integer a, Function<Integer, Integer> function) {
        return function.apply(a);
    }

}
