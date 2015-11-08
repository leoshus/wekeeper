package com.sdw.soft.demo.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdw.soft.demo.vo.User;

/**
 * author shangyd
 * date 2015年11月1日
 **/
@Controller
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping("/hello")
	public String demo(){
		
		return "hello";
	}
	
	@RequestMapping("/fetchUser")
	public @ResponseBody User fetchUser(){
		
		return new User("Jhon","admin",23,"beijing . changping");
	}
	
	@RequestMapping("/upUser")
	public @ResponseBody User upUser(@RequestBody User user){
		System.out.println(user);
		return user;
	}
}
