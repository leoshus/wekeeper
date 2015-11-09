package com.sdw.soft.wekeeper.common.vo;

import java.io.Serializable;

/**
 * @author shangyd
 * @date 2015年11月9日 下午5:33:07
 **/
public abstract class BaseEntity<ID extends Serializable> {

	private ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
}
