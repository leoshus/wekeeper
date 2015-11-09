package com.sdw.soft.wekeeper.common.user.vo;
/**
 * @author shangyd
 * @date 2015年11月9日 下午5:38:01
 **/
public enum UserStatus {
	normal(0),//"正常"
	blocked(1);//"锁定"
	
	private final int status;
	
	private UserStatus(int status){
		this.status = status;
	}
	
	public int getStatus(){
		return status;
	}
}
