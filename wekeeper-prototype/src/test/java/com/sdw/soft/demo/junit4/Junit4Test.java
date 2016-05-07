package com.sdw.soft.demo.junit4;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * author shangyd
 * date 2016年3月22日
 **/
public class Junit4Test {
	
	private static int a ;
	/**
	 * 针对所有测试只执行一次 必须修饰public static void
	 */
	@BeforeClass
	public static void beforeResource(){
		a = 100;
	}
	@Before
	public void beforedeal(){
		System.out.println("初始化方法 在任何测试case执行前必须执行的代码");
	}

	@Test(timeout=1000)
	public void test1(){
		System.out.println("test case");
	}
	
	@Ignore("ignore the testcase")
	@Test(expected=IllegalArgumentException.class)
	public void testException(){
		throw new IllegalArgumentException("invalid argument.");
	}
	
	/**
	 * @After 只能修饰public void方法
	 */
	@After
	public void afterdeal(){
		System.out.println("执行结束 收尾工作");
	}
	
	/**
	 * 针对所有测试 在所有测试结束后只执行一次 必须修饰public static void
	 */
	@AfterClass
	public static void afterResource(){
		System.out.println("释放资源 只执行一次。");
	}
}
