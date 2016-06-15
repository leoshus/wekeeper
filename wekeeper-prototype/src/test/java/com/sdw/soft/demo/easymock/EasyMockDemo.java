package com.sdw.soft.demo.easymock;

import junit.framework.Assert;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by shangyindong on 2016/6/15.
 */
public class EasyMockDemo {


    /**
     * Mock 单个对象
     */
    @Test
    public void testHttpServletRequest(){
       HttpServletRequest httpServletRequestMock = EasyMock.createMock(HttpServletRequest.class);
//        httpServletRequestMock.getAttribute("UserName");
//        EasyMock.expectLastCall().andReturn("Tom").times(1);
        EasyMock.expect(httpServletRequestMock.getAttribute("UserName")).andReturn("Tom").times(1);
        EasyMock.replay(httpServletRequestMock);
        Assert.assertEquals("test success","Tom",httpServletRequestMock.getAttribute("UserName"));
    }

    /**
     * Mock批量对象
     */
    @Test
    public void testMutiMockObject(){
        IMocksControl control = EasyMock.createControl();
        HttpServletRequest requestMock = control.createMock(HttpServletRequest.class);
        HttpServletResponse responseMock = control.createMock(HttpServletResponse.class);
        requestMock.getAttribute("UserName");
        EasyMock.expectLastCall().andReturn("Tom").times(1);
        try {
            responseMock.getWriter();
            EasyMock.expectLastCall().andReturn(new PrintWriter(System.out)).times(1);
            control.replay();
            responseMock.getWriter().write((String)requestMock.getAttribute("UserName"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
