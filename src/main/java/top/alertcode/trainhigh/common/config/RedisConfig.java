package top.alertcode.trainhigh.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author alertcode
 * @date 2018-08-14
 * @copyright alertcode.top
 */
@Configuration
public class RedisConfig {

  @Autowired
  RedisTemplate redisTemplate;

  @Bean
  public RedisTemplate redisTemplateInit() {
    //设置序列化Key的实例化对象
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    //设置序列化Value的实例化对象
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    return redisTemplate;
  }

}
