package com.github.herouu.trainhigh.design.strategy.aop;

import java.math.BigDecimal;

/**
 * 策略接口
 *
 * @author alertcode
 * @date 2018 -11-11
 * @copyright alertcode.top
 */
public interface StrategyInterface {


  /**
   * 运行策略
   */
  BigDecimal run();

}
