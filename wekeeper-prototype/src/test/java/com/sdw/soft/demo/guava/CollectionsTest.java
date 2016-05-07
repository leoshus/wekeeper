/**
 * @author shangyd
 * @Date 2015年12月5日 下午7:54:58
 * Copyright (c) 2015
 **/
package com.sdw.soft.demo.guava;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class CollectionsTest {
	public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
	        "red",
	        "orange",
	        "yellow",
	        "green",
	        "blue",
	        "purple");
	@Test
	public void test01(){
		ImmutableMap<String, String> map = ImmutableMap.of();
		
	}
	class Foo {
	    Set bars;
	    Foo(Set bars) {
	        this.bars = ImmutableSet.copyOf(bars); // defensive copy!
	    }
	}
}
