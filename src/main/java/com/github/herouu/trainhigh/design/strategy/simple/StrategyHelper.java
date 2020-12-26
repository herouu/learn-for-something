package com.github.herouu.trainhigh.design.strategy.simple;

/**
 * @author alertcode
 * @date 2018-11-11
 * @copyright alertcode.top
 */
public class StrategyHelper {

  private StrategyInterface strategyInterface;

  public StrategyHelper(StrategyInterface strategyInterface) {
    this.strategyInterface = strategyInterface;
  }

  public void operate() {
    this.strategyInterface.run();
  }

}
