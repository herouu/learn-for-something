package com.github.herouu.trainhigh.design.strategy.simple;

import com.github.herouu.trainhigh.design.strategy.simple.impl.StrategyOne;
import com.github.herouu.trainhigh.design.strategy.simple.impl.StrategyThree;
import com.github.herouu.trainhigh.design.strategy.simple.impl.StrategyTwo;

/**
 * @author alertcode
 * @date 2018-11-11
 * @copyright alertcode.top
 */
public class TestStrategy {

  public static void main(String[] args) {
    new StrategyHelper(new StrategyOne()).operate();
    new StrategyHelper(new StrategyTwo()).operate();
    new StrategyHelper(new StrategyThree()).operate();
  }
}
