package com.sdw.soft.wekeeper.common.helper;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.stereotype.Service;

import com.sdw.soft.core.utils.MD5Utils;
import com.sdw.soft.wekeeper.common.user.vo.SysUser;

/**
 * @author shangyd
 * @date 2015年11月10日 下午5:34:06
 **/
@Service
public class PasswordService {

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
}
