/**
 * @author shangyd
 * @Date 2016年4月27日 下午9:32:13
 * Copyright (c) 2015
 **/
package com.sdw.soft.demo.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

public class NettyServer {

	public static void main(String[] args){
		ServerBootstrap server = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		server.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new StringDecoder(),new StringEncoder(),new ServerHandler());
			}
		});
		Channel bind= server.bind(new InetSocketAddress(8080));
		System.out.println("Server has start at " + bind.getLocalAddress() + ",wait client to send message.");
	}
	
	private static class ServerHandler extends SimpleChannelHandler{
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
				throws Exception {
			if(e.getMessage() instanceof String){
				String message = (String)e.getMessage();
				System.out.println("Receive client's message :" + message);
				e.getChannel().write("Server has receive from client message :" + message);
				System.out.println("\n wait client to send message.");
			}
			super.messageReceived(ctx, e);
		}
		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
				throws Exception {
			super.exceptionCaught(ctx, e);
		}
		@Override
		public void channelConnected(ChannelHandlerContext ctx,
				ChannelStateEvent e) throws Exception {
			System.out.println("Find a client has reigster on this server");
			System.out.println("Client:" + e.getChannel().getRemoteAddress());
			System.out.println("Server:" + e.getChannel().getLocalAddress());
			System.out.println("Client Has connected ,server wait client send message.");
			super.channelConnected(ctx, e);
		}
	}
}
