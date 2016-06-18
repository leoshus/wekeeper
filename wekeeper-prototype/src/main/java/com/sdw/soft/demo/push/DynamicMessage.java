package com.sdw.soft.demo.push;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Random;

/**
 * Created by shangyindong on 2016/6/17.
 */
public class DynamicMessage implements Runnable {

    public static ChannelGroup audiences = new DefaultChannelGroup("msg-group");

    public static String message = "";

    public static String [] names = {
            "Tom", "Jerry",
            "Terry", "Looney",
            "Merrie", "William",
            "Joseph", "Hanna",
            "Speike", "Tyke",
            "Tuffy", "Lightning",
    };
    public static void addAudiences(Channel channel){
        audiences.add(channel);
    }
    public static void removeAudiences(Channel channel){
        audiences.remove(channel);
    }

    public static String getMessage(){
        StringBuffer sb = new StringBuffer();
        sb.append("hello,my name is ");
        sb.append(names[new Random().nextInt(names.length)]);
        sb.append(".");
        return sb.toString();
    }

    @Override
    public void run() {
        System.out.println("Dynamic Message start...");
        for(;;){
            String msg = getMessage();
            radiate(msg);
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void radiate(String msg){
        audiences.write(new TextWebSocketFrame(msg));
    }
}
