package top.alertcode.trainhigh.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.alertcode.trainhigh.TrainHighApplicationTests;

/**
 * @author alertcode
 * @date 2018-09-02
 * @copyright alertcode.top
 */
@Slf4j
public class TransactionalMysqlTest extends TrainHighApplicationTests {

  @Autowired
  TranscationService transcationService;


  @Test
  public void dirtyRead() {
    transcationService.listForDirtyRead();
  }

  @Test
  public void dirtyReadinsert() {
    transcationService.insertForDirtyReadAndIllusion();
  }

  //不可重复读
  @Test
  public void reread() {
    transcationService.listForIllusionRead();
  }

  @Test
  public void updateKdsPoint() {
    transcationService.updateForNoRepeat();
  }

  //幻读测试
  @Test
  public void listForPhantomRead() {
    transcationService.listForPhantomRead();
  }
  @Test
  public void insertForPhantomRead() {
    transcationService.insertFoPhantomReadAndIllusion();
  }
}
