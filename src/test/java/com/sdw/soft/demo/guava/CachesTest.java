/**
 * @author shangyd
 * @Date 2015年12月5日 下午3:28:11
 * Copyright (c) 2015
 **/
package com.sdw.soft.demo.guava;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class CachesTest {

	
	@Test
	public void test01(){
		LoadingCache<String,Integer> localCache = CacheBuilder.newBuilder()
				.maximumSize(1000).expireAfterWrite(30, TimeUnit.MINUTES)
				.build(new CacheLoader<String,Integer>(){
					@Override
					public Integer load(String key) throws Exception {
						return new Integer(1);
					}
		});
	}
}
