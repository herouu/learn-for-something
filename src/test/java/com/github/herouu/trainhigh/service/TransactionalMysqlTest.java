package com.github.herouu.trainhigh.service;

import com.github.herouu.trainhigh.TrainHighApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author alertcode
 * @date 2018-09-02
 * @copyright alertcode.top
 */
@Slf4j
public class TransactionalMysqlTest extends TrainHighApplicationTests {

  @Autowired
  TransactionalService transactionalService;

  //脏读
  @Test
  public void dirtyRead() {
    transactionalService.listForDirtyRead();
  }

  @Test
  public void dirtyReadinsert() {
    transactionalService.insertForDirtyReadAndIllusion();
  }

  //不可重复读
  @Test
  public void reread() {
    transactionalService.listForIllusionRead();
  }

  @Test
  public void updateKdsPoint() {
    transactionalService.updateForNoRepeat();
  }

  //幻读测试
  @Test
  public void listForPhantomRead() {
    transactionalService.listForPhantomRead();
  }
  @Test
  public void insertForPhantomRead() {
    transactionalService.insertFoPhantomReadAndIllusion();
  }
}
