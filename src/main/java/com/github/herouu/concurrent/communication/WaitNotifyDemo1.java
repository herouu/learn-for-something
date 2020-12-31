package com.github.herouu.concurrent.communication;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class WaitNotifyDemo1 {
    static boolean flag = true;
    static Object lock = new Object();

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("开启子线程。。。。");
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("获取原始值：" + flag);
                try {
                    System.out.println("子线程开始等待。。。");
                    // wait方法会释放当前对象的锁
                    lock.wait();
                    System.out.println("主线程通知子线程执行" + flag);
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("线程通知子线程等待。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "等待线程");
        thread.start();
        TimeUnit.SECONDS.sleep(3);

        synchronized (lock) {
            System.out.println("主线程开始通知，继续执行子线程");
            flag = false;
            // notify不会释放当前对象的锁,synchronized{}代码块执行完毕后，执行子线程剩余逻辑，子线程发现变量flag变量修改
            lock.notify();
            TimeUnit.SECONDS.sleep(4);
            System.out.println("主线程通知结束");
        }
        thread.join();
        System.out.println("子线程结束后主线程结束");
    }
}
