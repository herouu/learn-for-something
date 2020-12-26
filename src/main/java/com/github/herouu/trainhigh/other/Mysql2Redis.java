package com.github.herouu.trainhigh.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.github.herouu.trainhigh.service.QuestionService;

/**
 * @author alertcode
 * @date 2018-08-14
 * @copyright alertcode.top
 */
@Component
public class Mysql2Redis {

  @Autowired
  private QuestionService questionService;

  @Autowired
  private RedisTemplate redisTemplate;

  public static void main(String[] args) {
    //new Mysql2Redis().test();
  }



}
