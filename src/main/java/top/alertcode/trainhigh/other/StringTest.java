package top.alertcode.trainhigh.other;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;

/**
 * @author alertcode
 * @date 2018-10-19
 * @copyright alertcode.top
 */
public class StringTest {

  int a1 = 2;


  public static void main(String[] args) {
    ThreadInfo[] threadInfos = ManagementFactory.getThreadMXBean().dumpAllThreads(false, false);
    for (ThreadInfo threadInfo : threadInfos) {
      System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
    }
    while (true) {

    }
  }
}

