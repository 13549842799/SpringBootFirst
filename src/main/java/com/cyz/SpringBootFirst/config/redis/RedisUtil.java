package com.cyz.SpringBootFirst.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

}
