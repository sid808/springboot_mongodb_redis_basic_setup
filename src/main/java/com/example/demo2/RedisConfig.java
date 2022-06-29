package com.example.demo2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {


  @Value("${spring.redis.host}")
  private String REDIS_HOSTNAME;

  @Value("${spring.redis.port}")
  private int REDIS_PORT;

  @Value("${spring.redis.jedis.pool.max-idle}")
  private int maxIdle;

  @Value("${spring.redis.jedis.pool.min-idle}")
  private int minIdle;

  @Value("${spring.redis.jedis.pool.pool-size}")
  private int poolSize;

  @SuppressWarnings({"WeakerAccess", "ConstantConditions"})
  @Bean
  protected JedisConnectionFactory jedisConnectionFactory() {
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(REDIS_HOSTNAME, REDIS_PORT);
    JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build();
    JedisConnectionFactory factory = new JedisConnectionFactory(configuration,jedisClientConfiguration);
    factory.getPoolConfig().setMaxIdle(maxIdle);
    factory.getPoolConfig().setMinIdle(minIdle);
    factory.getPoolConfig().setMaxTotal(poolSize);
    factory.afterPropertiesSet();
    return factory;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    final RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashKeySerializer(new GenericToStringSerializer<>(Object.class));
    redisTemplate.setValueSerializer(new StringRedisSerializer());
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    return redisTemplate;
  }

}