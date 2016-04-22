package com.sdw.soft.demo.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author shangyd
 * @date 2016年4月22日 上午11:32:03
 **/
public class SpiDemo {

	public static void main(String[] args) {
		ServiceLoader<IDemoService> loader = ServiceLoader.load(IDemoService.class);
		Iterator<IDemoService> iterator = loader.iterator();
		while(iterator.hasNext()){
			IDemoService service = iterator.next();
			System.out.println(service.sayHello("hello world"));
		}
	}
}
