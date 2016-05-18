package com.sdw.soft.demo.dubbo.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.google.common.base.Joiner;
import com.sdw.soft.demo.dubbo.api.IDubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shangyd
 * @date 2016年5月7日 下午4:53:40
 **/
@Service(version = "1.0.0")
public class DubboServiceImpl implements IDubboService {

	private static final Logger logger = LoggerFactory.getLogger(DubboServiceImpl.class);

	@Override
	public String sayHello(String message) {
		boolean isConsumerSide = RpcContext.getContext().isConsumerSide();
		boolean isProviderSide = RpcContext.getContext().isProviderSide();
		logger.info(Joiner.on(" ").join(new Object[]{"isProviderSize=",isProviderSide,"isConsumerSide=",isConsumerSide}));
		String clientIp = RpcContext.getContext().getRemoteHost();//获取调用方IP
		logger.info("调用方的IP为:{}",clientIp);

		return "Dubbo say:" + message;
	}

}
