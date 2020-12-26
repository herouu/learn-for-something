package com.github.herouu.trainhigh.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author alertcode
 * @date 2018-10-22
 * @copyright alertcode.top
 */
public class TestExchanger {

  private static final Exchanger<String> ex = new Exchanger<>();
  private static ExecutorService service = Executors.newFixedThreadPool(2);

  public static void main(String[] args) {
    service.execute(() -> {
      String a = "a";
      try {
        ex.exchange(a);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    service.execute(() -> {
      String b = "b";
      try {
        //输出‘a’
        String exchange = ex.exchange(b);
        //进行校对工作
        System.out.println(exchange.equals(b));
        System.out.println(exchange);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    service.shutdown();
  }
}
