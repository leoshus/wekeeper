/**
 * @author shangyd
 * @Date 2016年1月10日 上午11:34:38
 * Copyright (c) 2015
 **/
package com.sdw.soft.demo.springdataredis.vo;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{

	private static final long serialVersionUID = -6538266414105835175L;
	
	private String name;
	private int age;
	private Date createDate;
	
	public User() {
		super();
	}
	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
		this.createDate = new Date();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
