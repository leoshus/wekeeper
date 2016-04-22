/**
 * @author shangyd
 * @Date 2015年12月5日 下午3:28:51
 * Copyright (c) 2015
 **/
package com.sdw.soft.demo.guava;

import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

public class StringsTest {

	@Test
	public void testString(){
		System.out.println(Joiner.on("-").skipNulls().join(new String[]{"Tom","Jack","Rose",null}));
		//1、判断字符串是否为空
		Strings.isNullOrEmpty("");
		//2、获得两个字符串相同的前缀或者后缀
		String a = "com.netease.reply.Hello";
		String b = "com.netease.reply.Hi";
		String commonPrefix = Strings.commonPrefix(a, b);
		System.out.println(commonPrefix);
		String commonSuffix = Strings.commonSuffix(a, b);
		System.out.println(commonSuffix);
		
		//3、Strings的padStart和padEnd方法来补全字符串
		int minLength = 4;
		String padEndResult = Strings.padEnd("123", minLength, '0');
		System.out.println("padEndResult is " + padEndResult);
		
		String padStartResult = Strings.padStart("1", 2, '0');
		System.out.println("padStartResult is " + padStartResult);
		
		//4、使用Splitter类来拆分字符串
		Iterable<String> splitResults = Splitter.onPattern("[,，]{1,}")
				.trimResults()
				.omitEmptyStrings()
				.split("hello,world,,世界，水平");
		for(String item : splitResults){
			System.out.println(item);
		}
		
		
	}
}
