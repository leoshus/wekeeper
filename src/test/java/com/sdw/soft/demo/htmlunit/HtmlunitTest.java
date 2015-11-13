package com.sdw.soft.demo.htmlunit;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author shangyd
 * @date 2015年11月13日 下午3:29:53
 **/
public class HtmlunitTest {

	@Test
	public void test01() throws Exception{
		
		WebClient client = new WebClient();
		HtmlPage page = client.getPage("http://www.163.com");
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		System.out.println(page.asText());
		client.closeAllWindows();
	}
}
