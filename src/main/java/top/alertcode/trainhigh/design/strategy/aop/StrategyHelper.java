package top.alertcode.trainhigh.design.strategy.aop;

import java.math.BigDecimal;

/**
 * @author alertcode
 * @date 2018-11-11
 * @copyright alertcode.top
 */
public class StrategyHelper {

  private ProxySimple proxySimple;

  public StrategyHelper(ProxySimple proxySimple) {
    this.proxySimple = proxySimple;
  }

  public BigDecimal operate() {
    return this.proxySimple.run();
  }

}
