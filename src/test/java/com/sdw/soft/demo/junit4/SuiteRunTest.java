package com.sdw.soft.demo.junit4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * author shangyd
 * date 2016年3月22日
 **/
@RunWith(Suite.class)
@SuiteClasses({Junit4Test.class,OnlyTest.class})
public class SuiteRunTest {

}
