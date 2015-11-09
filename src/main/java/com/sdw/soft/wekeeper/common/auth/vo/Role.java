package com.sdw.soft.wekeeper.common.auth.vo;

import com.sdw.soft.wekeeper.common.vo.BaseEntity;

/**
 * @author shangyd
 * @date 2015年11月9日 下午5:44:15
 **/
public class Role extends BaseEntity<Long> {

	private String name;
	private String role;
	private String description;
	private boolean isAvailable;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
}
