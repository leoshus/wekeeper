package com.sdw.soft.demo.dubbo.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sdw.soft.demo.dubbo.api.IDubboService;

/**
 * @author shangyd
 * @date 2016年5月7日 下午4:53:40
 **/
@Service(version = "1.0.0")
public class DubboServiceImpl implements IDubboService {

	@Override
	public String sayHello(String message) {
		return "Dubbo say:" + message;
	}

}
