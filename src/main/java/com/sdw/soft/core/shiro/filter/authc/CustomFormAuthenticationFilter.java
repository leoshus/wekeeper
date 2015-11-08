/**
 * @author shangyd
 * @Date 2015年11月8日 下午10:51:23
 * Copyright (c) 2015
 **/
package com.sdw.soft.core.shiro.filter.authc;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

	private String defaultSuccessUrl;

	public String getDefaultSuccessUrl() {
		return defaultSuccessUrl;
	}

	public void setDefaultSuccessUrl(String defaultSuccessUrl) {
		this.defaultSuccessUrl = defaultSuccessUrl;
	}
	
}
