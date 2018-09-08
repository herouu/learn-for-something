package top.alertcode.trainhigh.service;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import top.alertcode.trainhigh.mapper.KdsChapterPointMapper;

/**
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
 *
 * @author alertcode
 * @date 2018-09-02
 * @copyright alertcode.top
 */
@Service
@Slf4j
public class TransactionalService {

  @Autowired
  KdsChapterPointMapper kdsChapterPointMapper;

  @Autowired
  private JdbcTemplate jdbcTemplate;


  /**
   * 脏读 Isolation.READ_COMMITTED 及以上可以防止
   */
  @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
  public void listForDirtyRead() {
    List<Map<String, Object>> map = jdbcTemplate.queryForList("select * from sys_user ");
    log.info(JSON.toJSONString(map));
  }

  @Transactional(rollbackFor = Exception.class)
  public void insertForDirtyReadAndIllusion() {
    jdbcTemplate.execute(String.format(
        "INSERT INTO `kds_question_bank`.`sys_user` (`user_id`, `username`, `password`, `enable`, `user_type`, `phone`, `job_number`) VALUES ('22', 'wocaca', 'fda72a896de0b72fba9ae658f1067d81', '1', NULL, NULL, NULL)"));
    try {
      Thread.sleep(20000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    int a = 1 / 0;
  }


  /**
   * 不可重复读 针对（更新，插入操作）Isolation.REPEATABLE_READ及以上可以防止
   */
  @Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
  public void listForIllusionRead() {

    List<Map<String, Object>> map = jdbcTemplate.queryForList("select * from sys_user where user_id=1");
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    List<Map<String, Object>> map2 = jdbcTemplate.queryForList("select * from sys_user where user_id=1");

    Map<String, Object> res = new HashMap<String, Object>(16);
    res.put("before", map);
    res.put("after", map2);
    log.info(JSON.toJSONString(res));
  }


  public void updateForNoRepeat() {
    jdbcTemplate.execute("update  sys_user set username = 'zhangsan' where user_id=1");
  }

  /**
   * 幻读 REPEATABLE_READ及以上
   */
  @Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
  public void listForPhantomRead() {
    List<Map<String, Object>> map = jdbcTemplate.queryForList("select * from sys_user ");
    try {
      Thread.sleep(20000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    List<Map<String, Object>> map2 = jdbcTemplate.queryForList("select * from sys_user ");

    Map<String, Object> res = new HashMap<String, Object>(16);
    res.put("before", map);
    res.put("before.count",map.size());
    res.put("after", map2);
    res.put("after.count",map2.size());
    log.info(JSON.toJSONString(res));
  }

  @Transactional(rollbackFor = Exception.class)
  public void insertFoPhantomReadAndIllusion() {
    jdbcTemplate.execute("INSERT INTO `kds_question_bank`.`sys_user` (`user_id`, `username`, `password`, "
        + "`enable`, `user_type`, `phone`, `job_number`) VALUES ('22', 'wocaca', "
        + "'fda72a896de0b72fba9ae658f1067d81', '1', NULL, NULL, NULL)");
  }
}
