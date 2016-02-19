/**
 * @author shangyd
 * @Date 2015年12月5日 下午3:28:11
 * Copyright (c) 2015
 **/
package com.sdw.soft.demo.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class CachesTest {

	private static LoadingCache<String,AtomicInteger> localCache = CacheBuilder.newBuilder()
			.maximumSize(1000).expireAfterWrite(30, TimeUnit.MINUTES)//.expireAfterAccess(10, TimeUnit.MINUTES)
			.removalListener(new RemovalListener<String,AtomicInteger>(){
				@Override
				public void onRemoval(
						RemovalNotification<String, AtomicInteger> notification) {
					System.out.println(notification.getKey()+" has been removed");
				}
				
			})
			.build(new CacheLoader<String,AtomicInteger>(){
				@Override
				public AtomicInteger load(String key) throws Exception {
					return new AtomicInteger(0);
				}
	});
	
	@Test
	public void test01(){
		try {
			int num = localCache.get("abc").addAndGet(1);
			System.out.println("num1=" + num);
			System.out.println("abcd=" + localCache.get("abcd"));
			localCache.refresh("abc");//在生成新的value的时候，旧的value依然会被使用。
			num = localCache.get("abc").addAndGet(1);
			
			//删除指定key的缓存
			localCache.invalidate("abc");
//			localCache.invalidateAll(keys);
//			localCache.invalidateAll();
			
			System.out.println("num3=" + localCache.get("abc").get());
			System.out.println("num2=" + num);
			
			//Callable只有在缓存值不存在时，才会调用
			int count = localCache.get("abcde", new Callable<AtomicInteger>(){
				@Override
				public AtomicInteger call() throws Exception {
					return new AtomicInteger(3);
				}
			}).get();
			System.out.println("count=" + count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
