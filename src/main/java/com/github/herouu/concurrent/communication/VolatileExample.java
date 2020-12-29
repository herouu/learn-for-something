package com.github.herouu.concurrent.communication;

public class VolatileExample {

    /**
     * main 方法作为一个主线程
     */
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        // 开启线程
        myThread.start();

        // 主线程执行
        while (true) {
            if (myThread.isFlag()) {
                try {
                    Thread.sleep(1000);
                    System.out.println("主线程访问到 flag 变量");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

/**
 * 子线程类
 */
class MyThread extends Thread {

    private boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 修改变量值
        flag = true;
        System.out.println("flag = " + flag);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
