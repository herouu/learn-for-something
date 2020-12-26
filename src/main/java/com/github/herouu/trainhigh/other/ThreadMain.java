package com.github.herouu.trainhigh.other;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;
import lombok.extern.slf4j.Slf4j;

/**
 * Multithread computing类.
 *
 * @author Z佬
 * @version v1.0
 * @date 20180910 15:52:18
 */
@Slf4j
public class ThreadMain {

  private static long sum = 0L;

  public static void main(String[] args) {
    Instant start = Instant.now();
    for (long i = 1; i <= 1000000000; i++) {
      sum += i;
    }
    log.info("total result: " + sum);
    log.info("time:" + Duration.between(start, Instant.now()).toMillis());
  }
}

/**
 * Thread join add类.
 *
 * @author Z佬
 * @version v1.0
 * @date 20180910 15:52:18
 */
@Slf4j
class ThreadJoin {

  /**
   * sum.
   */
  public static long sum = 0;
  /**
   * LOCK.
   */
  public static Object LOCK = new Object();


  public static void test() throws InterruptedException {
    Instant start = Instant.now();
    ThreadJoin add = new ThreadJoin();
    ThreadTest thread1 = add.new ThreadTest(1, 250000000);
    ThreadTest thread2 = add.new ThreadTest(250000001, 500000000);
    ThreadTest thread3 = add.new ThreadTest(500000001, 750000000);
    ThreadTest thread4 = add.new ThreadTest(750000001, 1000000000);
    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();

    thread1.join();
    thread2.join();
    thread3.join();
    thread4.join();

    log.info("total result: " + sum);
    log.info("time:" + Duration.between(start, Instant.now()).toMillis());
  }

  /**
   * Thread test类.
   *
   * @author Z佬
   * @version v1.0
   * @date 20180910 15:52:18
   */
  class ThreadTest extends Thread {

    /**
     * The Begin.
     */
    private int begin;
    /**
     * The End.
     */
    private int end;

    /**
     * Instantiates a new Thread test.
     *
     * @param begin the begin
     * @param end the end
     */
    public ThreadTest(int begin, int end) {
      this.begin = begin;
      this.end = end;
    }

    @Override
    public void run() {
      synchronized (LOCK) {
        for (long i = begin; i <= end; i++) {
          sum += i;
        }
        log.info("from " + Thread.currentThread().getName() + " sum=" + sum);
      }
    }
  }
}

/**
 * Thread add latch类.
 *
 * @author Z佬
 * @version v1.0
 * @date 20180910 15:52:18
 */
@Slf4j
class ThreadCountDownLatch {

  /**
   * sum.
   */
  public static long sum = 0;
  /**
   * LOCK.
   */
  public static Object LOCK = new Object();
  /**
   * countdown.
   */
  public static CountDownLatch countdown = new CountDownLatch(4);

  /**
   * Test.
   *
   * @throws InterruptedException interrupted exception
   */
  public static void test() throws InterruptedException {
    Instant start = Instant.now();
    ThreadCountDownLatch add = new ThreadCountDownLatch();
    ThreadTest thread1 = add.new ThreadTest(1, 250000000);
    ThreadTest thread2 = add.new ThreadTest(250000001, 500000000);
    ThreadTest thread3 = add.new ThreadTest(500000001, 750000000);
    ThreadTest thread4 = add.new ThreadTest(750000001, 1000000000);
    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();

    countdown.await();
    log.info("total result: " + sum);
    log.info("time:" + Duration.between(start, Instant.now()).toMillis());
  }

  /**
   * Thread test类.
   *
   * @author Z佬
   * @version v1.0
   * @date 20180910 15:52:18
   */
  class ThreadTest extends Thread {

    /**
     * The Begin.
     */
    private int begin;
    /**
     * The End.
     */
    private int end;

    /**
     * Instantiates a new Thread test.
     *
     * @param begin the begin
     * @param end the end
     */
    public ThreadTest(int begin, int end) {
      this.begin = begin;
      this.end = end;
    }

    @Override
    public void run() {
      synchronized (LOCK) {
        for (long i = begin; i <= end; i++) {
          sum += i;
        }
        log.info("from " + Thread.currentThread().getName() + " sum=" + sum);
      }
      countdown.countDown();
    }
  }
}


/**
 * Thread add barrier类.
 *
 * @author Z佬
 * @version v1.0
 * @date 20180910 15:52:18
 */
@Slf4j
class ThreadCyclicBarrier {

  /**
   * start.
   */
  public static final Instant start = Instant.now();
  /**
   * sum.
   */
  public static long sum = 0;
  /**
   * LOCK.
   */
  public static Object LOCK = new Object();

  /**
   * cyclicbarrier.
   */
  public static CyclicBarrier cyclicbarrier = new CyclicBarrier(4,
      () -> {
        log.info("" + sum);
        log.info("time:" + Duration.between(start, Instant.now()).toMillis());
      });

  /**
   * Test.
   *
   * @throws InterruptedException interrupted exception
   */


  public static void main(String[] args) {
    ThreadCyclicBarrier add = new ThreadCyclicBarrier();
    ThreadTest thread1 = add.new ThreadTest(1, 250000000);
    ThreadTest thread2 = add.new ThreadTest(250000001, 500000000);
    ThreadTest thread3 = add.new ThreadTest(500000001, 750000000);
    ThreadTest thread4 = add.new ThreadTest(750000001, 1000000000);
    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();
  }

  /**
   * Thread test类.
   *
   * @author Z佬
   * @version v1.0
   * @date 20180910 15:52:18
   */
  class ThreadTest extends Thread {

    /**
     * The Begin.
     */
    private int begin;
    /**
     * The End.
     */
    private int end;

    /**
     * Instantiates a new Thread test.
     *
     * @param begin the begin
     * @param end the end
     */
    public ThreadTest(int begin, int end) {
      this.begin = begin;
      this.end = end;
    }

    @Override
    public void run() {
      synchronized (LOCK) {
        for (int i = begin; i <= end; i++) {
          sum += i;
        }
        log.info("from " + Thread.currentThread().getName() + " sum=" + sum);
      }
      try {
        cyclicbarrier.await();
      } catch (InterruptedException | BrokenBarrierException e) {
        e.printStackTrace();
      }
    }
  }
}


/**
 * Thread add future类.
 *
 * @author Z佬
 * @version v1.0
 * @date 20180910 15:52:18
 */
@Slf4j
class ThreadFuture {

  /**
   * futureList.
   */
  public static List<Future> futureList = new ArrayList<Future>();

  /**
   * Test.
   *
   * @throws InterruptedException interrupted exception
   * @throws ExecutionException execution exception
   */
  public static void test() throws InterruptedException, ExecutionException {
    Instant start = Instant.now();
    long sum = 0;
    ThreadFuture add = new ThreadFuture();
    ExecutorService pool = Executors.newFixedThreadPool(4);

    for (long i = 1; i <= 750000001; ) {
      ThreadTest thread = add.new ThreadTest(i, i + 249999999);
      Future<Long> future = pool.submit(thread);
      futureList.add(future);
      i += 250000000;
    }

    if (futureList != null && futureList.size() > 0) {
      for (Future<Long> future : futureList) {
        sum += (Long) future.get();
      }
    }
    pool.shutdown();
    log.info("total result: " + sum);
    log.info("time:" + Duration.between(start, Instant.now()).toMillis());
  }

  /**
   * Thread test类.
   *
   * @author Z佬
   * @version v1.0
   * @date 20180910 15:52:18
   */
  class ThreadTest implements Callable<Long> {

    /**
     * The Sum.
     */
    public long sum = 0;
    /**
     * The Begin.
     */
    private long begin;
    /**
     * The End.
     */
    private long end;

    /**
     * Instantiates a new Thread test.
     *
     * @param begin the begin
     * @param end the end
     */
    public ThreadTest(long begin, long end) {
      this.begin = begin;
      this.end = end;
    }

    @Override
    public Long call() throws Exception {
      for (long i = begin; i <= end; i++) {
        sum += i;
      }
      log.info("from " + Thread.currentThread().getName() + " sum=" + sum);
      return sum;
    }
  }
}


/**
 * Fork join类.
 *
 * @author Z佬
 * @version v1.0
 * @date 20180910 15:52:18
 */
@Slf4j
class ThreadForkJoin {

  /**
   * Test.
   *
   * @throws Exception exception
   */
  public static void test() throws Exception {
    Instant start = Instant.now();
    ForkJoinPool pool = new ForkJoinPool();
    Future<Long> result = pool.submit(new ThreadTest(0L, 1000000000L));
    log.info("total result: " + result.get());
    log.info("time:" + Duration.between(start, Instant.now()).toMillis());
  }

  /**
   * Fork join test类.
   *
   * @author Z佬
   * @version v1.0
   * @date 20180910 15:52:18
   */
  static class ThreadTest extends RecursiveTask<Long> {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The Threshold.
     */
    private final Long THRESHOLD = 100L;
    /**
     * The Start.
     */
    private Long start;
    /**
     * The End.
     */
    private Long end;

    /**
     * Instantiates a new Fork join test.
     *
     * @param start the start
     * @param end the end
     */
    public ThreadTest(Long start, Long end) {
      this.start = start;
      this.end = end;
    }

    @Override
    protected Long compute() {
      Long sum = 0L;
      if ((end - start) < THRESHOLD) {
        for (Long i = start; i <= end; i++) {
          sum += i;
        }
      } else {
        Long middle = (start + end) / 2;
        ThreadTest left = new ThreadTest(start, middle);
        ThreadTest right = new ThreadTest(middle + 1, end);
        left.fork();
        right.fork();

        sum = left.join() + right.join();
      }
      return sum;
    }
  }
}

/**
 * Stream test类.
 *
 * @author Z佬
 * @version v1.0
 * @date 20180910 15:52:18
 */
@Slf4j
class ThreadStream {

  /**
   * Test.
   */
  public static void test() {
    Instant start = Instant.now();
    long l = iterateStream(1000000000);
    log.info("total result:" + l);
    log.info("StreamTest time:" + Duration.between(start, Instant.now()).toMillis());
  }

  /**
   * Iterate stream long.
   *
   * @param limit limit
   * @return long
   */
  private static long iterateStream(long limit) {
    return LongStream.rangeClosed(1, limit).parallel().reduce(0L, Long::sum);
  }
}
