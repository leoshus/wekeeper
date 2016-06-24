package com.sdw.soft.demo.protobuf;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by shangyindong on 2016/6/24.
 */
public class CodecDemo {

    @Test
    public void testCodec(){
        UserInfo.User.PhoneNumber phoneNumber = UserInfo.User.PhoneNumber.newBuilder().setNumber("123456").setType(UserInfo.User.PhoneType.HOME).build();
        UserInfo.User user = UserInfo.User.newBuilder()
                .setId(1)
                .setName("Tom")
                .setEmail("test@163.com")
                .setPhone(phoneNumber).build();
        byte[] bytes = user.toByteArray();
        try {
            IOUtils.write(bytes,new FileOutputStream(new File("D:/1.bat")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testDeCodec(){
        try {
            UserInfo.User user = UserInfo.User.parseFrom(new FileInputStream(new File("D:/1.bat")));
            System.out.println(user.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
