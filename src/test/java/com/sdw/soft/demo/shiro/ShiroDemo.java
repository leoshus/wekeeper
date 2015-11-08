package com.sdw.soft.demo.shiro;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * author shangyd
 * date 2015年11月7日
 **/
public class ShiroDemo {
	
	@Test
	public void testHelloShiro(){
		try {
			//获取SecurityManager工厂，此处使用ini配置初始化SecurityManager
			Factory<org.apache.shiro.mgt.SecurityManager> factory = 
//					new IniSecurityManagerFactory("classpth:/com/sdw/soft/demo/shiro/shiro.ini");
			new IniSecurityManagerFactory("classpath:shiro/shiro.ini");
			//得到SecurityManager实例 并绑定SecurityUtils
			org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
			SecurityUtils.setSecurityManager(securityManager);
			//得到Subject及创建用户名/密码身份验证Token
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken("hello","123");
		
			//登录
			subject.login(token);
			System.out.println(subject.isAuthenticated());
			Assert.assertEquals(true, subject.isAuthenticated());//断言用户已经登录
			//退出
			subject.logout();
		} catch (Exception e) {
			//身份认证失败
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testCustomRealm(){
		try {
			Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro-realm.ini");
			org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
			SecurityUtils.setSecurityManager(securityManager);
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken("hello", "123");
			
			subject.login(token);
			
			System.out.println(subject.isAuthenticated());
			subject.logout();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
