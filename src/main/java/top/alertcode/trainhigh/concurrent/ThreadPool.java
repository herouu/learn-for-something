package top.alertcode.trainhigh.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author alertcode
 * @date 2018-10-21
 * @copyright alertcode.top
 */
public class ThreadPool {

  static volatile int i = 0;
  private static Object lock = new Object();
  private static CountDownLatch end=new CountDownLatch(1);
  public static void main(String[] args) throws InterruptedException {

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 1, TimeUnit.DAYS,
        new LinkedBlockingQueue<>());
    threadPoolExecutor.execute(new Runnable() {
      @Override
      public void run() {
        while (i < 100) {
          //synchronized (lock) {
            i++;
          //}
          System.out.println(i);
        }
        end.countDown();
      }
    });
    end.await();
    threadPoolExecutor.shutdown();
  }
}
