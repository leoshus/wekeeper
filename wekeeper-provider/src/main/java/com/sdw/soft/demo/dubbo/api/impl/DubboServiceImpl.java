package com.sdw.soft.demo.dubbo.api.impl;

import com.sdw.soft.demo.dubbo.api.IDubboService;

/**
 * @author shangyd
 * @date 2016年5月7日 下午4:53:40
 **/
public class DubboServiceImpl implements IDubboService {

	@Override
	public void sayHello(String message) {
		System.out.println("Dubbo say:" + message);
	}

}
