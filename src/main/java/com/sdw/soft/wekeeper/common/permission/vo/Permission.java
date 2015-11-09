package com.sdw.soft.wekeeper.common.permission.vo;

import com.sdw.soft.wekeeper.common.vo.BaseEntity;

/**
 * @author shangyd
 * @date 2015年11月9日 下午5:45:36
 **/
public class Permission extends BaseEntity<Long> {

	private String name;
	private String permission;
	private String decription;
	private boolean isAvailable;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
}
