package com.github.herouu.process;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ProcessDemo {


    public static void main(String[] args) throws IOException, InterruptedException {
        Process process =
                Runtime.getRuntime().exec("java com.github.herouu.process.EndlessLoop", null,
                        new File("E:\\project\\train-high\\src\\main\\java"));

        // jvm启动的ee
        // Process process = Runtime.getRuntime().exec("fork.exe");
        // 等待进程
        process.waitFor(20, TimeUnit.SECONDS);
        // 销毁进程
        process.destroy();
    }
}
