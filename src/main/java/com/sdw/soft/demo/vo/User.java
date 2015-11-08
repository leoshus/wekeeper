package com.sdw.soft.demo.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * author shangyd
 * date 2015年11月4日
 **/
@XmlRootElement
public class User implements Serializable {

	private static final long serialVersionUID = 7287075907028242220L;
	
	private String username;
	private String password;
	private int age;
	private String address;
	
	public User() {
		super();
	}
	public User(String username, String password, int age, String address) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
		this.address = address;
	}
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "username=" + username + ",password=" + password + ",age=" + age + ",address=" + address;
	}
}
