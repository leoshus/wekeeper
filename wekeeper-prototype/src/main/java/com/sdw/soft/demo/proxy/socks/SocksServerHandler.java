package com.sdw.soft.demo.proxy.socks;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;
import org.jboss.netty.handler.codec.socks.*;

/**
 * Created by shangyindong on 2018/1/23.
 */
public class SocksServerHandler extends SimpleChannelUpstreamHandler {

    private static final String name = "SOCKS_SERVER_HANDLER";

    public static String getName() {
        return name;
    }

    private final ClientSocketChannelFactory cf;

    public SocksServerHandler(ClientSocketChannelFactory cf) {
        this.cf = cf;
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        SocksRequest socksRequest = (SocksRequest) e.getMessage();
        switch (socksRequest.getSocksRequestType()) {
            case INIT:
                ctx.getPipeline().addFirst(SocksCmdRequestDecoder.getName(),new SocksCmdRequestDecoder());
                ctx.getChannel().write(new SocksInitResponse(SocksMessage.AuthScheme.NO_AUTH));
                break;
            case AUTH:
                ctx.getPipeline().addFirst(SocksCmdRequestDecoder.getName(),new SocksCmdRequestDecoder());
                ctx.getChannel().write(new SocksAuthResponse(SocksMessage.AuthStatus.SUCCESS));
                break;
            case CMD:
                SocksCmdRequest request = (SocksCmdRequest) socksRequest;
                if (request.getCmdType() == SocksMessage.CmdType.CONNECT) {
                    ctx.getPipeline().addLast(SocksServerConnectHandler.getName(), new SocksServerConnectHandler(cf));
                    ctx.getPipeline().remove(this);
                } else {
                    ctx.getChannel().close();
                }
                break;
            case UNKNOWN:
                break;
        }
        super.messageReceived(ctx, e);
    }
}
