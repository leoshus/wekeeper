package com.sdw.soft.wekeeper.common.user.vo;

import java.util.Date;

import com.sdw.soft.wekeeper.common.vo.BaseEntity;

/**
 * author shangyd
 * date 2015年11月8日
 **/
public class SysUser extends BaseEntity{

	private String username;
	private String password;
	private String salt;
	private UserStatus userStatus;
	private boolean isAdmin;
	private boolean hasdel;
	private Date createTime;
	private Date modifyTime;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public UserStatus getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean isHasdel() {
		return hasdel;
	}
	public void setHasdel(boolean hasdel) {
		this.hasdel = hasdel;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}
