package com.sdw.soft.demo.web.action;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sdw.soft.demo.dubbo.api.IDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by shangyd on 16/5/8.
 */
@Controller("dubboController")
@RequestMapping(value = "/dubbo")
public class DubboController {

    @Reference(version = "1.0.0")
    private IDubboService dubboService;

    @RequestMapping("/demo")
    public void dubboDemo(HttpServletRequest request,HttpServletResponse response){
        try {
            PrintWriter writer = response.getWriter();
            writer.write(dubboService.sayHello("dubbo controller"));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
