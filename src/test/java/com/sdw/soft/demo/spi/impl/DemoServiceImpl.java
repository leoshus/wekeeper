package com.sdw.soft.demo.spi.impl;

import com.google.common.base.Joiner;
import com.sdw.soft.demo.spi.IDemoService;

/**
 * @author shangyd
 * @date 2016年4月22日 上午11:33:21
 **/
public class DemoServiceImpl implements IDemoService {

	@Override
	public String sayHello(String message) {
		return Joiner.on(":").useForNull("").join("Java SPI say",message);
	}

}
