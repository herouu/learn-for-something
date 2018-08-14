package top.alertcode.trainhigh.other;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.io.ResolverUtil.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import top.alertcode.trainhigh.common.domian.KdsChapterPoint;
import top.alertcode.trainhigh.service.QuestionService;

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
