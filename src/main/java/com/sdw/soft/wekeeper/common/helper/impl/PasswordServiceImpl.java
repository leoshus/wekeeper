package com.sdw.soft.wekeeper.common.helper.impl;

import java.util.Random;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.stereotype.Service;

import com.sdw.soft.core.utils.MD5Utils;
import com.sdw.soft.wekeeper.common.helper.PasswordService;
import com.sdw.soft.wekeeper.common.user.vo.SysUser;

/**
 * @author shangyd
 * @date 2015年11月10日 下午5:34:06
 **/
@Service
public class PasswordServiceImpl implements PasswordService{

	public void validate(SysUser user,String password){
		if(!matches(user, password)){
			throw new IncorrectCredentialsException("密码不匹配");
		}
	}
	
	public boolean matches(SysUser user,String password){
		return user.getPassword().equals(encryptPassword(user.getUsername(), password, user.getSalt()));
	}
	public String encryptPassword(String username,String password,String salt){
		return MD5Utils.hash(username + password + salt); 
	}
	
	public SysUser encryptPassword(SysUser user){
		user.setSalt(generateSalt(4));
		user.setPassword(encryptPassword(user.getUsername(), user.getPassword(), user.getSalt()));
		return user;
	}
	
	private String generateSalt(int n){
		Random random = new Random();
		StringBuffer sb = new StringBuffer(n);
		for(int i = 0;i < n;i++){
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
}
