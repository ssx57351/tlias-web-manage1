package com.itheima.configuration;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfiguration {

    @Bean
    @ConditionalOnSingleCandidate
    //@Resource(name = "redisConnectionFactory")
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){

        log.info("开始创建redis模版类");
        RedisTemplate redisTemplate=new RedisTemplate();
        //设置 key的序列化器，默认为JdkSerializationRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;

    }
}
