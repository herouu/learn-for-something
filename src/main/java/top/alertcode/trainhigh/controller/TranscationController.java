package top.alertcode.trainhigh.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author alertcode 用于测试mysql 事务
 *     事务隔离级别	               脏读	不可重复读	幻读
 *     读未提交（read-uncommitted）	是	    是	    是
 *     不可重复读（read-committed）	否	    是	    是
 *     可重复读（repeatable-read）	否	    否	    是
 *     串行化（serializable）	      否	    否	    否
 *
 *
 *     1. ISOLATION_DEFAULT： 这是一个PlatfromTransactionManager默认的隔离级别，使用数据库默认的事务隔离级别,另外四个与JDBC的隔离级别相对应
 *     2. ISOLATION_READ_UNCOMMITTED： 这是事务最低的隔离级别，它充许令外一个事务可以看到这个事务未提交的数据,这种隔离级别会产生脏读，不可重复读和幻像读。
 *     3. ISOLATION_READ_COMMITTED： 保证一个事务修改的数据提交后才能被另外一个事务读取。另外一个事务不能读取该事务未提交的数据
 *     4. ISOLATION_REPEATABLE_READ： 这种事务隔离级别可以防止脏读，不可重复读。但是可能出现幻像读,它除了保证一个事务不能读取另一个事务未提交的数据外，还保证了避免下面的情况产生
 *     (不可重复读)。
 *     5. ISOLATION_SERIALIZABLE 这是花费最高代价但是最可靠的事务隔离级别。事务被处理为顺序执行,除了防止脏读，不可重复读外，还避免了幻像读
 * @date 2018-09-02
 * @copyright alertcode.top
 */
@RestController
public class TranscationController {


  /**
   * 脏读(Dirty Read)—— 一个事务读取到了另外一个事务没有提交的数据。
   * 不可重复读（Nonrepeatable Read）——在同一事务中，两次读取同一数据，得到内容不同
   * 幻读（Phantom）—— 同一事务中，用同样的操作读取两次，得到的记录数不相同
   *
   */
  public void dirtyRead() {

  }
}
