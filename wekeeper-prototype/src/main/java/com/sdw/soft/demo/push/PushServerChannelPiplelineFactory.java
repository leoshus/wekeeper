package com.sdw.soft.demo.push;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;
import org.jboss.netty.handler.timeout.WriteTimeoutHandler;
import org.jboss.netty.util.HashedWheelTimer;

/**
 * Created by shangyindong on 2016/6/17.
 */
public class PushServerChannelPiplelineFactory implements ChannelPipelineFactory {

    @Override
    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline cp = Channels.pipeline();
        cp.addLast("decoder",new HttpRequestDecoder());
        cp.addLast("encoder",new HttpResponseEncoder());
        cp.addLast("writeTimeout",new WriteTimeoutHandler(new HashedWheelTimer(),10));
        cp.addLast("handler",new PushServerChannelHandler());
        return cp;
    }
}
