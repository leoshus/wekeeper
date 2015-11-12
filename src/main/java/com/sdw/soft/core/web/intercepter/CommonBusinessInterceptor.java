/**
 * @author shangyd
 * @Date 2015年11月12日 下午9:26:14
 * Copyright (c) 2015
 **/
package com.sdw.soft.core.web.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sdw.soft.core.utils.Constant;

public class CommonBusinessInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(CommonBusinessInterceptor.class);

	private final PathMatcher pathMatcher = new AntPathMatcher();
	
	private String[] excludeUrls;
	
	public void setExcludeUrls(String[] excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(isExcludeUrl(request)){
			return true;
		}
		
		if(request.getAttribute(Constant.CONTEXT_PATH) == null){
			request.setAttribute(Constant.CONTEXT_PATH, request.getContextPath());
		}
		logger.info("common business interceptor ... ");
		return true;
	}
	
	private boolean isExcludeUrl(final HttpServletRequest request){
		if(this.excludeUrls == null){
			return false;
		}
		for(String excludeUrl:excludeUrls){
			if(pathMatcher.match(excludeUrl, request.getServletPath())){
				return true;
			}
		}
		return false;
	}
}
