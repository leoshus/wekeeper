package com.sdw.soft.wekeeper.common.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdw.soft.wekeeper.common.helper.PasswordService;
import com.sdw.soft.wekeeper.common.user.dao.UserDao;
import com.sdw.soft.wekeeper.common.user.service.UserService;
import com.sdw.soft.wekeeper.common.user.vo.SysUser;

/**
 * @author shangyd
 * @date 2015年11月9日 下午7:06:57
 **/
@Service
public class UserServiceImpl implements UserService {

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Autowired
	private PasswordService passwordService;
	
	@Override
	public int save(SysUser user) {
		return userDao.saveUser(user);
	}

	@Override
	public List<SysUser> listUser() {
		return userDao.listUser();
	}

	@Override
	public SysUser findUserById(long id) {
		return userDao.findUserById(id);
	}

	@Override
	public SysUser login(String username, String password) {
		SysUser user = userDao.findUserByName(username);
		if(null == user){
			throw new UnknownAccountException("未知用户");
		}
		passwordService.validate(user, password);
		return user;
	}

}
