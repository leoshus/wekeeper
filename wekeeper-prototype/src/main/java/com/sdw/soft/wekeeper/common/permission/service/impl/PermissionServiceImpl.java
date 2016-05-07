package com.sdw.soft.wekeeper.common.permission.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sdw.soft.wekeeper.common.auth.vo.RoleToPermission;
import com.sdw.soft.wekeeper.common.permission.dao.PermissionDao;
import com.sdw.soft.wekeeper.common.permission.service.PermissionService;
import com.sdw.soft.wekeeper.common.permission.vo.Permission;

/**
 * @author shangyd
 * @date 2015年11月10日 下午4:13:29
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

	@Resource(name="permissionDao")
	private PermissionDao permissionDao;
	
	@Override
	public int savePermission(Permission permission) {
		return permissionDao.savePermission(permission);
	}

	@Override
	public List<Permission> listPermission() {
		return permissionDao.listPermission();
	}

	@Override
	public Permission findPermissionById(long id) {
		return permissionDao.findPermissionById(id);
	}

	@Override
	public int saveRoleToPermission(RoleToPermission roleToPermission) {
		return permissionDao.saveRoleToPermission(roleToPermission);
	}

	@Override
	public List<Permission> findPermissionByRoleId(long roleId) {
		return permissionDao.findPermissionByRoleId(roleId);
	}

}
