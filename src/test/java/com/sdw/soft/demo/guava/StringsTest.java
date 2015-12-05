/**
 * @author shangyd
 * @Date 2015年12月5日 下午3:28:51
 * Copyright (c) 2015
 **/
package com.sdw.soft.demo.guava;

import org.junit.Test;

import com.google.common.base.Joiner;

public class StringsTest {

	@Test
	public void test01(){
		System.out.println(Joiner.on("-").skipNulls().join(new String[]{"Tom","Jack","Rose",null}));
	}
}
