package com.sdw.soft.demo.push;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by shangyindong on 2016/6/17.
 */
public class PushServer implements InitializingBean,Runnable {

    private static final Logger logger = LoggerFactory.getLogger(PushServer.class);

    private int port = 8099;

    public PushServer() {
    }

    public PushServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        logger.info("Chat Server Port:{}" , port);
        ServerBootstrap server = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),Executors.newCachedThreadPool()));
        server.setOption("child.tcpNoDelay",true);
        server.setOption("child.keepAlive",true);
        server.setPipelineFactory(new PushServerChannelPiplelineFactory());
        server.bind(new InetSocketAddress(port));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        /*Thread thread = new Thread(new DynamicMessage(),"DynamicMessage");
        thread.setDaemon(true);
        thread.start();*/
        new PushServer(8099).run();
    }

    public static void main(String[] args){
        Thread thread = new Thread(new DynamicMessage(),"DynamicMessage");
        thread.setDaemon(true);
        thread.start();
        new PushServer(8099).run();
    }
}
