/**
 * @author shangyd
 * @Date 2016年4月27日 下午9:40:32
 * Copyright (c) 2015
 **/
package com.sdw.soft.demo.netty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

public class NettyClient {

	public static void main(String[] args) {
		ClientBootstrap client = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		client.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new StringDecoder(),new StringEncoder(),new ClientHandler());
			}
		});
		ChannelFuture future = client.connect(new InetSocketAddress("localhost",8080));
		future.getChannel().getCloseFuture().awaitUninterruptibly();
		client.releaseExternalResources();
	}
	
	private static class ClientHandler extends SimpleChannelHandler{
		private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
				throws Exception {
			if(e.getMessage() instanceof String){
				String message = (String)e.getMessage();
				System.out.println("Client has receive server message :" + message);
				e.getChannel().write(reader.readLine());
				System.out.println("wait client to write message:");
			}
			super.messageReceived(ctx, e);
		}
		@Override
		public void connectRequested(ChannelHandlerContext ctx,
				ChannelStateEvent e) throws Exception {
			System.out.println("client has build connection with server.");
			System.out.println("\n please write the message :");
			super.connectRequested(ctx, e);
			e.getChannel().write(reader.readLine());
		}
	}
}
