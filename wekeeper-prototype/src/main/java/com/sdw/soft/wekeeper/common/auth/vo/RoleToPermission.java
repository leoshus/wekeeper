package com.sdw.soft.wekeeper.common.auth.vo;

import com.sdw.soft.wekeeper.common.vo.BaseEntity;

/**
 * @author shangyd
 * @date 2015年11月9日 下午5:51:10
 **/
public class RoleToPermission extends BaseEntity {

	private Long roleId;
	private Long permissionId;
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
}
