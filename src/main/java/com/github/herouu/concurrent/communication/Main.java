package com.github.herouu.concurrent.communication;

/**
 * 1.最常见的情况，主线程中开启了一个子线程，开启之后，主线程与子线程互不影响各自的生命周期，即主线程结束，子线程还可以继续执行；子线程结束，主线程也能继续执行
 * 2.主线程开启了子线程，但是主线程结束，子线程也随之结束 设置子线程为守护线程
 * 3.主线程开启了一个子线程，主线程必须要等子线程运行完之后，才能结束主线程 子线程thread.join
 */
public class Main {

    public static void main(String[] args) {
        // 1.最常见的情况，主线程中开启了一个子线程，开启之后，主线程与子线程互不影响各自的生命周期，即主线程结束，子线程还可以继续执行；子线程结束，主线程也能继续执行
        System.out.println("主线程启动。。。。");
        Thread thread = new Thread(new ChildThread());
        // 2.主线程开启了子线程，但是主线程结束，子线程也随之结束 设置子线程为守护线程
        // thread.setDaemon(true);
        thread.start();
        // 3.主线程开启了一个子线程，主线程必须要等子线程运行完之后，才能结束主线程 子线程thread.join
        // try {
        //     thread.join();
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        System.out.println("主线程结束。。。。");
    }
}

class ChildThread implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("子线程启动。。。。");
            Thread.sleep(5000);
            System.out.println("子线程结束。。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
