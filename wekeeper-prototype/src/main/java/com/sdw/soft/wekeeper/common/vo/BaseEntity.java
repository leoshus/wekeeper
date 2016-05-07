package com.sdw.soft.wekeeper.common.vo;

import java.io.Serializable;

/**
 * @author shangyd
 * @date 2015年11月9日 下午5:33:07
 **/
public abstract class BaseEntity{

	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
