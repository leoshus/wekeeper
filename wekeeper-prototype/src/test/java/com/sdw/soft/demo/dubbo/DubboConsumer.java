package com.sdw.soft.demo.dubbo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sdw.soft.demo.dubbo.api.IDubboService;

/**
 * @author shangyd
 * @date 2016年5月7日 下午4:52:03
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:dubbo/dubbo-consumer.xml"})
public class DubboConsumer {

	@Autowired
	private IDubboService dubboService;
	
	@Test
	public void test01(){
		dubboService.sayHello("success");
	}
}
