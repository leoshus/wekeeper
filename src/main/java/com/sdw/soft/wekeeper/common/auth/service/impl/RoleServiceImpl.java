package com.sdw.soft.wekeeper.common.auth.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdw.soft.wekeeper.common.auth.dao.RoleDao;
import com.sdw.soft.wekeeper.common.auth.service.RoleService;
import com.sdw.soft.wekeeper.common.auth.vo.Role;
import com.sdw.soft.wekeeper.common.user.vo.UserToRole;

/**
 * @author shangyd
 * @date 2015年11月10日 下午3:46:50
 **/
@Service
public class RoleServiceImpl implements RoleService{

	@Resource(name="roleDao")
	private RoleDao roleDao;
	
	@Override
	public int saveRole(Role role) {
		return roleDao.saveRole(role);
	}

	@Override
	public List<Role> listRole() {
		return roleDao.listRole();
	}

	@Override
	public Role findRoleById(long id) {
		return roleDao.findRoleById(id);
	}

	@Override
	public int saveUserToRole(UserToRole userToRole) {
		return roleDao.saveUserToRole(userToRole);
	}

	@Override
	public List<Role> findRoleByUser(long userId) {
		return roleDao.findRoleByUser(userId);
	}

}
