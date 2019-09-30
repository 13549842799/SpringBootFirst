package com.cyz.SpringBootFirst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TestController {
	
	@Autowired
	private RedisTemplate<String, Object> redis;
	
	@Autowired
	private StringRedisTemplate stringRedis;
	
	@RequestMapping("/test")
	public String test() {
		//redis.opsForValue().set("1", "achsdadfads");
		stringRedis.opsForValue().set("ab10", "哈哈哈");
		return "测试";
	}

}
