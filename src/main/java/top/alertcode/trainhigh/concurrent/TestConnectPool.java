package top.alertcode.trainhigh.concurrent;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author alertcode
 * @date 2018-10-21
 * @copyright alertcode.top
 */
public class TestConnectPool {

  static ConnectPool connectPool = new ConnectPool(10);
  static CountDownLatch start = new CountDownLatch(1);
  static CountDownLatch end;

  public static void main(String[] args) throws InterruptedException {
    int threadCount = 10;
    end = new CountDownLatch(threadCount);
    int count = 20;
    AtomicInteger got = new AtomicInteger();
    AtomicInteger notgot = new AtomicInteger();
    for (int i = 0; i < threadCount; i++) {
      new Thread(new ConnectRunner(count, got, notgot), "ConnectionRunner").start();
    }
    start.countDown();
    end.await();
    System.out.println("total invoke" + (threadCount * count));
    System.out.println("got connection" + got);
    System.out.println("notgot connect" + notgot);
  }

  static class ConnectRunner implements Runnable {

    int count;
    AtomicInteger got;
    AtomicInteger notgot;

    public ConnectRunner(int count, AtomicInteger got, AtomicInteger notgot) {
      this.count = count;
      this.got = got;
      this.notgot = notgot;
    }

    @Override
    public void run() {
      try {
        start.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      while (count > 0) {
        try {
          Connection connection = connectPool.fetchConnect(1000);
          if (Objects.nonNull(connection)) {
            try {
              connection.createStatement();
              connection.commit();
            } catch (SQLException e) {

            } finally {
              connectPool.releaseConnection(connection);
              got.incrementAndGet();
            }
          } else {
            notgot.incrementAndGet();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          count--;
        }
      }
      end.countDown();
    }
  }
}


