package top.alertcode.trainhigh.concurrent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * The type Connect pool.
 *
 * @author alertcode
 * @date 2018 -10-21
 * @copyright alertcode.top
 */
public class ConnectPool {

  private LinkedList<Connection> pools = new LinkedList<Connection>();

  /**
   * 初始化连接池大小
   *
   * @param size
   *     the size
   */
  public ConnectPool(int size) {
    for (int i = 0; i < size; i++) {
      pools.addLast(ConnectDriver.createConnect());
    }
  }

  /**
   * 释放连接池连接
   *
   * @param connection
   *     the connection
   */
  public void releaseConnection(Connection connection) {
    if (Objects.nonNull(connection)) {
      synchronized (pools) {
        pools.addLast(connection);
        pools.notifyAll();
      }
    }
  }


  /**
   * 获取连接池连接
   *
   * @param mills
   *     the mills
   * @return the connection
   * @throws InterruptedException
   *     the interrupted exception
   */
  public Connection fetchConnect(long mills) throws InterruptedException {
    synchronized (pools) {
      if (mills <= 0) {
        while (pools.isEmpty()) {
          pools.wait();
        }
        return pools.removeFirst();
      } else {
        long future = System.currentTimeMillis() + mills;
        long remaining = mills;
        while (pools.isEmpty() && remaining > 0) {
          pools.wait();
          remaining = future - System.currentTimeMillis();
        }
        if (!pools.isEmpty()) {
          return pools.removeFirst();
        }
        return null;
      }
    }
  }


}

/**
 * JDK 动态代理
 */
class ConnectDriver {

  /**
   * The type Connect handler.
   */
  static class ConnectHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if (method.getName().equalsIgnoreCase("commit")) {
        TimeUnit.MILLISECONDS.sleep(100);
      }
      return null;
    }
  }


  public static final Connection createConnect() {
    return (Connection) Proxy
        .newProxyInstance(ConnectHandler.class.getClassLoader(), new Class[]{Connection.class},
            new ConnectHandler());
  }
}
