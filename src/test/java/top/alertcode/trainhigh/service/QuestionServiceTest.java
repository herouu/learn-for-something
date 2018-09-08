package top.alertcode.trainhigh.service;

import com.alibaba.fastjson.JSON;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import top.alertcode.trainhigh.TrainHighApplicationTests;
import top.alertcode.trainhigh.common.domian.KdsChapterPoint;

public class QuestionServiceTest extends TrainHighApplicationTests {

  @Autowired
  private QuestionService questionService;
  @Autowired
  private RedisTemplate redisTemplate;

  @Test
  public void test() {
    ValueOperations valueOperations = redisTemplate.opsForValue();
    valueOperations.set("1", "1");
    Object o = valueOperations.get("1");
    System.out.println(o);
  }

  @Test
  public void run() {
    List<Map<Object,Object>> kdsChapterPoints = questionService.querySqlQuestions();
    HashOperations hashOperations = redisTemplate.opsForHash();
    if (CollectionUtils.isNotEmpty(kdsChapterPoints)) {
      for (Map kdsChapterPoint : kdsChapterPoints) {
        hashOperations.putAll(String.valueOf(kdsChapterPoint.get("id")),kdsChapterPoint);
      }
    }
  }
  @Test
  public void getHash(){
    Map keys = redisTemplate.opsForHash().entries("329507");
    System.out.println(JSON.toJSONString(keys));
  }

  @Rule
  public ContiPerfRule i = new ContiPerfRule();
  @Test
  @PerfTest(invocations = 100,threads = 10)
  public void testContiPerf() {
    List<KdsChapterPoint> kdsChapterPoints = questionService.querySqlQuestion(329503L);
    //断言 是否和预期一致
    System.out.println(JSON.toJSONString(kdsChapterPoints));
  }


  @Test
  public void insertSqlQuestion() {
  }

  @Test
  public void updateSqlQuestion() {
  }

  @Test
  public void deleteSqlQuestion() {
  }

  @Test
  public void querySqlQuestion() {
    List<KdsChapterPoint> kdsQuestionChapters = questionService.querySqlQuestion(329504L);
    System.out.println(kdsQuestionChapters.size());
  }

  @Test
  public void insertRedisQuestion() {
  }

  @Test
  public void updateRedisQuestion() {
  }

  @Test
  public void deleteRedisQuestion() {
  }

  @Test
  public void queryRedisQuestion() {
  }
}