package top.alertcode.trainhigh.design.strategy.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import top.alertcode.trainhigh.design.strategy.aop.impl.StrategyOne;
import top.alertcode.trainhigh.design.strategy.aop.impl.StrategyThree;
import top.alertcode.trainhigh.design.strategy.aop.impl.StrategyTwo;

/**
 * @author alertcode
 * @date 2018-11-11
 * @copyright alertcode.top
 */
public class TestStrategy {

  public static void main(String[] args) {
    testProxyHandler();
  }

  public static void testSimpleProxy() {
    BigDecimal operate = new StrategyHelper(new ProxySimple(new StrategyOne())).operate();
    System.out.println(operate);
    BigDecimal operate1 = new StrategyHelper(new ProxySimple(new StrategyTwo())).operate();
    System.out.println(operate1);
    BigDecimal operate2 = new StrategyHelper(new ProxySimple(new StrategyThree())).operate();
    System.out.println(operate2);
  }

  public static void testProxyHandler() {
    StrategyInterface StrategyOne = new StrategyOne();
    InvocationHandler invoke = new ProxyHandler(StrategyOne);
    StrategyInterface instance = (StrategyInterface) Proxy
        .newProxyInstance(StrategyOne.getClass().getClassLoader(), new Class[]{StrategyInterface.class},
            invoke);
    System.out.println(instance.run());
    StrategyInterface strategyTwo = new StrategyTwo();
    InvocationHandler invoke1 = new ProxyHandler(strategyTwo);
    StrategyInterface strategyInterface2 = (StrategyInterface) Proxy
        .newProxyInstance(strategyTwo.getClass().getClassLoader(), new Class[]{StrategyInterface.class},
            invoke1);
    System.out.println(strategyInterface2.run());
    StrategyInterface strategyThree = new StrategyThree();
    InvocationHandler invoke3 = new ProxyHandler(strategyThree);
    StrategyInterface strategyInterface3 = (StrategyInterface) Proxy
        .newProxyInstance(strategyThree.getClass().getClassLoader(), new Class[]{StrategyInterface.class},
            invoke3);
    System.out.println(strategyInterface3.run());
  }
}
