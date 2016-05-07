/**
 * @author shangyd
 * @Date 2016年1月10日 上午11:31:50
 * Copyright (c) 2015
 **/
package com.sdw.soft.demo.springdataredis;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;

import com.sdw.soft.demo.springdataredis.vo.User;

public class SpringDataRedisTest {

	@Test
	public void test01(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:context/spring-data-redis.xml");
		RedisTemplate template = (RedisTemplate) context.getBean("jedisTemplate");
		//其中key采取了StringRedisSerializer  
        //其中value采取JdkSerializationRedisSerializer  
		ValueOperations<String, User> valueOper = template.opsForValue();
		User user1 = new User("Tom", 23);
		User user2 = new User("Jery",25);
//		valueOper.set("u:u1", user1);
//		valueOper.set("u:u2", user2);
		System.out.println(valueOper.get("u:u1").getName());
		System.out.println(valueOper.get("u:u2").getName());
		
		ValueOperations<String, User> jsonOper = template.opsForValue();
		template.setValueSerializer(new JacksonJsonRedisSerializer<User>(User.class));
//		jsonOper.set("json:u1", user1);
//		jsonOper.set("json:u2", user2);
		System.out.println(jsonOper.get("json:u1").getName());
		System.out.println(jsonOper.get("json:u2").getName());
		
		HashOperations<String,String,User> hashOper = template.opsForHash();
		hashOper.put("user:map", "u:u1", user1);
		hashOper.put("user:map", "u:u2", user2);
		System.out.println(hashOper.get("user:map", "u:u1").getName());
		System.out.println(hashOper.get("user:map", "u:u2").getName());
		
	}
}
