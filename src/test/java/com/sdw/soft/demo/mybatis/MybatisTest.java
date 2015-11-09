package com.sdw.soft.demo.mybatis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;

import com.sdw.soft.wekeeper.common.user.service.UserService;

/**
 * @author shangyd
 * @date 2015年11月9日 下午7:10:51
 **/
@ContextConfiguration(locations="classpath*:/context/spring-context.xml, classpath*:/context/spring-profiles.xml")
@Profile("mysql")
public class MybatisTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void test01(){
		
	}
}
