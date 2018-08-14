package top.alertcode.trainhigh.service;

import com.alibaba.fastjson.JSON;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
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