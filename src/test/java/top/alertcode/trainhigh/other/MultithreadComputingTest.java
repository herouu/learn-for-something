package top.alertcode.trainhigh.other;

import java.util.concurrent.ExecutionException;
import org.junit.Test;

public class MultithreadComputingTest {

  @Test
  public void testThreadMain() {
    ThreadMain.main(new String[]{});
  }

  @Test
  public void testForkJoin() throws Exception {
    ForkJoin.test();
  }

  @Test
  public void testThreadJoin() throws InterruptedException {
    ThreadJoin.test();
  }

  @Test
  public void testThreadCountDownLatch() throws InterruptedException {
    ThreadCountDownLatch.test();
  }

  @Test
  public void testThreadCyclicBarrier() throws InterruptedException {
    ThreadCyclicBarrier.main(new String[]{});
  }

  @Test
  public void testThreadFuture() throws ExecutionException, InterruptedException {
    ThreadFuture.test();
  }

  @Test
  public void testThreadStream() {
    ThreadStream.test();
  }

}