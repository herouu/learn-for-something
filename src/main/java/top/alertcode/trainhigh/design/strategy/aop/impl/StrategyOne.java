package top.alertcode.trainhigh.design.strategy.aop.impl;

import java.math.BigDecimal;
import top.alertcode.trainhigh.design.strategy.aop.StrategyInterface;

/**
 * @author alertcode
 * @date 2018-11-11
 * @copyright alertcode.top
 */
public class StrategyOne implements StrategyInterface {

  @Override
  public BigDecimal run() {
    System.out.println("执行策略one");
    return BigDecimal.ONE;
  }
}
