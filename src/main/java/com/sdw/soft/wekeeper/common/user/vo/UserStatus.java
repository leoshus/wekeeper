package com.sdw.soft.wekeeper.common.user.vo;

import com.sdw.soft.core.mybatis.handler.ValuedEnum;

/**
 * @author shangyd
 * @date 2015年11月9日 下午5:38:01
 **/
public enum UserStatus implements ValuedEnum{
	normal(0),//"正常"
	blocked(1);//"锁定"
	
	private int value;
	
	private UserStatus(int value){
		this.value = value;
	}
	
	@Override
	public int getValue() {
		return value;
	}
}
