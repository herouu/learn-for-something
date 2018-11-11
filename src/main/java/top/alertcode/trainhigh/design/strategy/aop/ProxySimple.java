package top.alertcode.trainhigh.design.strategy.aop;

import java.math.BigDecimal;

/**
 * @author alertcode
 * @date 2018-11-11
 * @copyright alertcode.top
 */
public class ProxySimple implements StrategyInterface {

  private StrategyInterface strategyInterface;

  public ProxySimple() {
    this.strategyInterface = new ProxySimple();
  }

  public ProxySimple(StrategyInterface strategyInterface) {
    this.strategyInterface = strategyInterface;
  }

  private void before() {
    System.out.println("run 方法，执行之旗调用");
  }

  @Override
  public BigDecimal run() {
    before();
    return after(this.strategyInterface.run());
  }

  private BigDecimal after(BigDecimal x) {
    System.out.println("run 方法执行之后调用·");
    return x.setScale(2, BigDecimal.ROUND_HALF_UP);
  }
}
