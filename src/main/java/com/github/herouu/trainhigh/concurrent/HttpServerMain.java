package com.github.herouu.trainhigh.concurrent;

public class HttpServerMain {

    public static void main(String[] args) {
        SimpleHttpServer shs = new SimpleHttpServer();
        shs.setBasePath("D:/basedir"); //将根目录定义到html所在的目录
        try {
            shs.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
