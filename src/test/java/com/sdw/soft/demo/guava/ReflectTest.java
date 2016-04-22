/**
 * @author shangyd
 * @Date 2015年12月5日 下午3:29:50
 * Copyright (c) 2015
 **/
package com.sdw.soft.demo.guava;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.junit.Test;

import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;

public class ReflectTest {

	@Test
	public void test01(){
		TypeToken<String> stringTok = TypeToken.of(String.class);
		TypeToken<Integer> integerTok = TypeToken.of(Integer.class);
		TypeToken<List<String>> stringListTok = new TypeToken<List<String>>(){};//空匿名内部类
		TypeToken<Map<?,?>> wildMapTok = new TypeToken<Map<?,?>>(){};//泛型通配符类型参数
		System.out.println(stringTok.getType());
		System.out.println(integerTok.getType());
		System.out.println(stringListTok.getType());
		System.out.println(wildMapTok.getType());
		
		TypeToken<Map<String,BigInteger>> mapToken = mapToken(TypeToken.of(String.class), TypeToken.of(BigInteger.class));
		TypeToken<Map<Integer,Queue<String>>> complexToken = mapToken(TypeToken.of(Integer.class), new TypeToken<Queue<String>>(){});
		System.out.println(complexToken.getType());
		System.out.println(complexToken.getRawType());
		
		
	}
	//动态的解决泛型类型参数
	static <K,V> TypeToken<Map<K,V>> mapToken(TypeToken<K> keyToken,TypeToken<V> valueToken){
		return new TypeToken<Map<K,V>>(){}
			.where(new TypeParameter<K>(){}, keyToken)
			.where(new TypeParameter<V>(){}, valueToken); 
	}
}
