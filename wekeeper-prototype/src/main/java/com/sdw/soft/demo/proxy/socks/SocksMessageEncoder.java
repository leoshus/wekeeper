package com.sdw.soft.demo.proxy.socks;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.jboss.netty.handler.codec.socks.SocksMessage;

/**
 * Created by shangyindong on 2018/1/23.
 */
@ChannelHandler.Sharable
public class SocksMessageEncoder extends OneToOneEncoder {

    private static final String name = "SOCKS_MESSAGE_ENCODER";
    private static final int DEFAULT_ENCODER_BUFFER_SIZE = 1024;

    public static String getName() {
        return name;
    }


    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
        ChannelBuffer buffer = null;
        if (msg instanceof SocksMessage) {
            buffer = ChannelBuffers.buffer(DEFAULT_ENCODER_BUFFER_SIZE);
            ((SocksMessage)msg).encodeAsByteBuf(buffer);
        } else if (msg instanceof ChannelBuffer) {
            buffer = (ChannelBuffer) msg;
        }
        return buffer;
    }
}
