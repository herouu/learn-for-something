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
 * @author alertcode
 * @date 2018-09-02
 * @copyright alertcode.top
 */
@Service
@Slf4j
public class TranscationService {

  @Autowired
  KdsChapterPointMapper kdsChapterPointMapper;

  public final static String STR_POINT_NAME = "test-transcation";

  @Autowired
  private JdbcTemplate jdbcTemplate;


  //脏读 Isolation.READ_COMMITTED 及以上可以防止
  @Transactional(isolation = Isolation.READ_COMMITTED)
  public void listForDirtyRead() {
    List<Map<String, Object>> map = jdbcTemplate.queryForList("select * from sys_user ");
    log.info(JSON.toJSONString(map));
  }

  @Transactional
  public void insertForDirtyReadAndIllusion() {
    jdbcTemplate.execute("INSERT INTO `kds_question_bank`.`sys_user` (`user_id`, `username`, `password`, "
        + "`enable`, `user_type`, `phone`, `job_number`) VALUES ('22', 'wocaca', "
        + "'fda72a896de0b72fba9ae658f1067d81', '1', NULL, NULL, NULL)");
    try {
      Thread.sleep(20000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    int a = 1 / 0;
  }


  //不可重复读 针对（更新，插入操作）Isolation.REPEATABLE_READ及以上可以防止
  @Transactional(isolation = Isolation.REPEATABLE_READ)
  public void listForIllusionRead() {

    List<Map<String, Object>> map = jdbcTemplate.queryForList("select * from sys_user where user_id=1");
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    List<Map<String, Object>> map2 = jdbcTemplate.queryForList("select * from sys_user where user_id=1");

    Map<String, Object> res = new HashMap<String, Object>();
    res.put("before", map);
    res.put("after", map2);
    log.info(JSON.toJSONString(res));
  }


  public void updateForNoRepeat() {
    jdbcTemplate.execute("update  sys_user set username = 'zhangsan' where user_id=1");
  }

  //幻读 REPEATABLE_READ及以上
  @Transactional(isolation = Isolation.REPEATABLE_READ)
  public void listForPhantomRead() {
    List<Map<String, Object>> map = jdbcTemplate.queryForList("select * from sys_user ");
    try {
      Thread.sleep(20000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    List<Map<String, Object>> map2 = jdbcTemplate.queryForList("select * from sys_user ");

    Map<String, Object> res = new HashMap<String, Object>();
    res.put("before", map);
    res.put("before.count",map.size());
    res.put("after", map2);
    res.put("after.count",map2.size());
    log.info(JSON.toJSONString(res));
  }

  @Transactional
  public void insertFoPhantomReadAndIllusion() {
    jdbcTemplate.execute("INSERT INTO `kds_question_bank`.`sys_user` (`user_id`, `username`, `password`, "
        + "`enable`, `user_type`, `phone`, `job_number`) VALUES ('22', 'wocaca', "
        + "'fda72a896de0b72fba9ae658f1067d81', '1', NULL, NULL, NULL)");
  }
}
