package com.sdw.soft.demo.junit4;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.base.Joiner;


/**
 * author shangyd
 * date 2016年3月22日
 **/
@RunWith(Parameterized.class)
public class ParameterizedRunTest {
	
	private String param1;
	private String param2;
	private String result;
	
	public ParameterizedRunTest(String param1,String param2,String result){
		this.param1 = param1;
		this.param2 = param2;
		this.result = result;
	}
	
	@Test
	public void test01(){
		System.out.println(Joiner.on("-").useForNull("").join(new String[]{param1,param2,result}));
	}
	
	@Parameters
	public static Collection getData(){
		return Arrays.asList(new Object[][]{
				{"Tom","25","Tom:25"},
				{"Rose","22","Rose:22"},
				{"Jack","23","Jack:23"}
				});
	}
}
