package com.github.herouu.trainhigh.design.strategy.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * @author alertcode
 * @date 2018-11-11
 * @copyright alertcode.top
 */
public class ProxyHandler implements InvocationHandler {

  private StrategyInterface strategyInterface;

  public ProxyHandler(StrategyInterface strategyInterface) {
    this.strategyInterface = strategyInterface;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    before();
    Object invoke = method.invoke(this.strategyInterface, args);
    return after(invoke);
  }

  private void before() {
    System.out.println("执行开始前调用");
  }

  private BigDecimal after(Object invoke) {
    System.out.println("执行开始后调用");
    return ((BigDecimal) invoke).setScale(2, BigDecimal.ROUND_HALF_UP);
  }
}
