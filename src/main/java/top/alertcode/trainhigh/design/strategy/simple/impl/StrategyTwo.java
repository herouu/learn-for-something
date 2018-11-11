package top.alertcode.trainhigh.design.strategy.simple.impl;

import top.alertcode.trainhigh.design.strategy.simple.StrategyInterface;

/**
 * @author alertcode
 * @date 2018-11-11
 * @copyright alertcode.top
 */
public class StrategyTwo implements StrategyInterface {

  @Override
  public void run() {
    System.out.println("执行策略two");
  }
}
