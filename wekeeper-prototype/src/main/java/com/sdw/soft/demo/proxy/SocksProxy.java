package com.sdw.soft.demo.proxy;

import com.sdw.soft.demo.proxy.socks.SocksProxyPipelineFactory;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by shangyindong on 2018/1/23.
 */
public class SocksProxy {


    public static void main(String[] args) {
        Executor bossExecutor = Executors.newCachedThreadPool();
        Executor workerExecutor = Executors.newCachedThreadPool();
        ServerBootstrap serverBootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(bossExecutor, workerExecutor));

        ClientSocketChannelFactory cf = new NioClientSocketChannelFactory(bossExecutor, workerExecutor);
        serverBootstrap.setPipelineFactory(new SocksProxyPipelineFactory(cf));

        serverBootstrap.bind(new InetSocketAddress(1080));
    }
}
