package com.sdw.soft.demo.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sdw.soft.wekeeper.common.user.service.UserService;
import com.sdw.soft.wekeeper.common.user.vo.SysUser;
import com.sdw.soft.wekeeper.common.user.vo.UserStatus;

/**
 * @author shangyd
 * @date 2015年11月9日 下午7:10:51
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/context/spring-context.xml"})
@ActiveProfiles("mysql")
public class MybatisTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void test01(){
		SysUser user = new SysUser();
		user.setId(1L);
		user.setUsername("Tom");
		user.setPassword("123");
		user.setSalt("hello");
		user.setUserStatus(UserStatus.normal);
		user.setAdmin(true);
		user.setHasdel(false);
		int num = userService.save(user);
		System.out.println(num);
	}
}
