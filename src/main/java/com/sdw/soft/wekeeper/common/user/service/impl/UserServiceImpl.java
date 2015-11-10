package com.sdw.soft.wekeeper.common.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdw.soft.wekeeper.common.auth.vo.Role;
import com.sdw.soft.wekeeper.common.user.dao.UserDao;
import com.sdw.soft.wekeeper.common.user.service.UserService;
import com.sdw.soft.wekeeper.common.user.vo.SysUser;
import com.sdw.soft.wekeeper.common.user.vo.UserToRole;

/**
 * @author shangyd
 * @date 2015年11月9日 下午7:06:57
 **/
@Service
public class UserServiceImpl implements UserService {

	@Resource(name="userDao")
	private UserDao userDao;
	
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

}
