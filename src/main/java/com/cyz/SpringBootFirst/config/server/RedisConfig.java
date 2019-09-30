package com.cyz.SpringBootFirst.config.server;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration.LettuceClientConfigurationBuilder;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class) //当配置这个注解时，引入的配置了@ConfigurationProperties注解的类无需添加@Component注解也会被注入到IOC中
public class RedisConfig  extends CachingConfigurerSupport {
	
	private StringRedisTemplate template;

	@Autowired
	private RedisProperties properties;

	@Bean
    public LettuceConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("39.97.99.250", 6379);
		configuration.setPassword(properties.getPassword());
		configuration.setDatabase(properties.getDatabase());
		LettuceConnectionFactory f = new LettuceConnectionFactory(configuration);
        return f;
    }
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> redis = new RedisTemplate<>();
		redis.setConnectionFactory(factory);
		redis.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		
		StringRedisSerializer serializer = new StringRedisSerializer();
		redis.setKeySerializer(serializer);
		redis.setHashKeySerializer(serializer);
		return redis;
	}
	
	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate redis = new StringRedisTemplate(factory);
		
		return redis;
	}

	/*@Override
	public CacheErrorHandler errorHandler() {
		
		return new IgnoreExceptionCacheErrorHandler
	}

	*/
	  

	   /* @SuppressWarnings("unchecked")
		@Bean
	    public CacheManager cacheManager(RedisConnectionFactory factory) {
	        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
	        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

	        //解决查询缓存转换异常的问题
	        ObjectMapper om = new ObjectMapper();
	        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	        jackson2JsonRedisSerializer.setObjectMapper(om);

	        // 配置序列化（解决乱码的问题）
	        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
	                                            .entryTtl(Duration.ZERO)
	                                            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
	                                            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
	                                            .disableCachingNullValues();

	        RedisCacheManager cacheManager = RedisCacheManager.builder(factory).cacheDefaults(config).build();
	        return cacheManager;
	    }*/
}
