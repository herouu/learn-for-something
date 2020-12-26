package com.github.herouu.trainhigh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.herouu.trainhigh.common.domian.KdsChapterPoint;
import com.github.herouu.trainhigh.mapper.KdsChapterPointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Question service类.
 *
 * @author Z佬
 * @version v1.0
 * @date 20180807 9:11:22
 */
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

  @Autowired
  private KdsChapterPointMapper kdsChapterPoint;

  @Autowired
  private RedisTemplate redisTemplate;

  @Override
  public void insertSqlQuestion() {
  }

  @Override
  public void updateSqlQuestion() {

  }

  @Override
  public void deleteSqlQuestion() {

  }

  @Override
  public List<KdsChapterPoint> querySqlQuestion(long id) {
    ArrayList<KdsChapterPoint> objects = new ArrayList<>();
    KdsChapterPoint KdsChapterPoint = kdsChapterPoint.selectByPrimaryKey(id);
    objects.add(KdsChapterPoint);
    return objects;
  }

  @Override
  public List<Map<Object, Object>> querySqlQuestions() {
    return kdsChapterPoint.selectByExample(null);
  }

  @Override
  public void insertRedisQuestion() {

  }

  @Override
  public void updateRedisQuestion() {

  }

  @Override
  public void deleteRedisQuestion() {

  }

  @Override
  public Map queryRedisQuestion(Integer id) {
    Map entries = redisTemplate.opsForHash().entries(String.valueOf(id));
    return entries;
  }
}
