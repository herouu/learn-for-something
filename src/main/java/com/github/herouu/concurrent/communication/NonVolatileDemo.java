package com.github.herouu.concurrent.communication;

public class NonVolatileDemo {
    // 只考虑可见性，不考虑原子的性的操作,状态位
    // public static volatile boolean stop = false;//任务是否停止,普通变量
    public static boolean stop = false;//任务是否停止,普通变量

    public static void main(String[] args) throws Exception {
        Thread thread1 = new Thread(() -> {
            while (true) {
                if (stop) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("stop=true，满足条件。");
                    return;
                }
            }

        });
        thread1.start();

        Thread.sleep(1000);//保证主线程修改stop=true，在子线程启动后执行。
        stop = true; //true
        System.out.println("主线程设置停止标识 stop=true。" +
                "设置时间：" + System.currentTimeMillis());
    }
}
