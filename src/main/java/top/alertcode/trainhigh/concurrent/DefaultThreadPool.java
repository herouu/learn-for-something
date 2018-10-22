package top.alertcode.trainhigh.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The type Default thread pool.
 *
 * @param <Job>
 *     the type parameter
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

  //  线程池最大限制数
  private static final int MAX_WORKER_NUMBERS = 10;
  //  线程池默认数量
  private static final int DEFAULT_WORK_NUMBER = 5;
  //  线程池最小数量
  private static final int MIN_WORKER_NUMBER = 1;
  //  工作列表，负责插入
  private final LinkedList<Job> jobs = new LinkedList<>();
  //  工作者列表
  private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>());
  //工作者线程数量
  private int workerNum = DEFAULT_WORK_NUMBER;
  //线程编号生成
  private AtomicLong threadNum = new AtomicLong();

  public DefaultThreadPool(int num) {
    workerNum =
        num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : num < MIN_WORKER_NUMBER ? MIN_WORKER_NUMBER : num;
    initWorkers(workerNum);
  }

  public DefaultThreadPool() {
    initWorkers(DEFAULT_WORK_NUMBER);
  }

  private void initWorkers(int num) {
    for (int i = 0; i < num; i++) {
      Worker worker = new Worker();
      workers.add(worker);
      Thread thread = new Thread(worker, "threadpool-worker-" + threadNum.getAndIncrement());
      thread.start();
    }
  }

  @Override
  public void execute(Job job) {
    if (Objects.nonNull(job)) {
      synchronized (jobs) {
        jobs.addLast(job);
        jobs.notify();
      }
    }
  }

  @Override
  public void shutdown() {
    for (Worker worker : workers) {
      worker.shutdown();
    }
  }

  @Override
  public void addWorkers(int num) {
    synchronized (jobs) {
//      限制新增的worker数量不能超过最大值
      if (num + this.workerNum > MAX_WORKER_NUMBERS) {
        num = MAX_WORKER_NUMBERS - this.workerNum;
      }
      initWorkers(num);
      this.workerNum += num;
    }
  }

  @Override
  public void removeWorker(int num) {
    synchronized (jobs) {
      if (num >= this.workerNum) {
        throw new IllegalArgumentException("beyond workernum");
      }
      int count = 0;
      while (count < num) {
        Worker worker = workers.get(count);
        if (workers.remove(worker)) {
          worker.shutdown();
          count++;
        }
      }
      this.workerNum -= count;
    }
  }

  @Override
  public int getJobSize() {
    return jobs.size();
  }

  class Worker implements Runnable {

    private volatile boolean running = true;

    @Override
    public void run() {
      while (running) {
        Job job = null;
        synchronized (jobs) {
          while (jobs.isEmpty()) {
            try {
              jobs.wait();
            } catch (InterruptedException e) {
//              感知外部对workers的中断操作，返回
              Thread.currentThread().interrupt();
              return;
            }
          }
          job = jobs.removeFirst();
        }
        if (Objects.nonNull(job)) {
          try {
            job.run();
          } catch (Exception e) {
            //忽略job执行中的exception
          }
        }
      }
    }


    public void shutdown() {
      running = false;
    }
  }
}


