package com.sdw.soft.demo.push;

import org.jboss.netty.channel.*;
import org.jboss.netty.handler.codec.http.*;
import org.jboss.netty.handler.codec.http.websocketx.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by shangyindong on 2016/6/17.
 */
public class PushServerChannelHandler extends SimpleChannelHandler {

    private static final Logger logger = LoggerFactory.getLogger(PushServerChannelHandler.class);

    private WebSocketServerHandshaker handshaker;

    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        logger.info("Find a channel open");
        DynamicMessage.addAudiences(e.getChannel());
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        Channel channel = e.getChannel();
        Object message = e.getMessage();
        logger.info("message Type is :{}",message.getClass());

        try {
            if(message instanceof HttpRequest){
                processHttpRequest(ctx,(HttpRequest)message);
            }else if(message instanceof WebSocketFrame){
                processWebsocketRequest(ctx,(WebSocketFrame)message);
            }else{
                //other type request
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            channel.close().sync();
        }
        super.messageReceived(ctx, e);
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        logger.info("Channel Closed");
        if(e instanceof MessageEvent){
            MessageEvent me = (MessageEvent)e;
        }
        DynamicMessage.removeAudiences(e.getChannel());
        e.getChannel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        logger.info("Find channel Closed");
        DynamicMessage.removeAudiences(e.getChannel());
        e.getCause().printStackTrace();
        e.getChannel().close();
        try {
            super.exceptionCaught(ctx,e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void processHttpRequest(ChannelHandlerContext ctx , HttpRequest request){
        Channel channel = ctx.getChannel();
        List<Map.Entry<String, String>> list = request.getHeaders();
        for(Map.Entry<String,String> entry : list){
            logger.info("header ----{}:{}" ,entry.getKey(),entry.getValue());
        }
        if(!HttpMethod.GET.equals(request.getMethod())){
            DefaultHttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.BAD_REQUEST);
            channel.write(response);
            channel.close();
            return ;
        }
        WebSocketServerHandshakerFactory wsShakerFactory = new WebSocketServerHandshakerFactory("ws://" + request.getHeader(HttpHeaders.Names.HOST),null,false);
        handshaker = wsShakerFactory.newHandshaker(request);
        if (null == handshaker){
            //Can't deal the version of the websockt
            wsShakerFactory.sendUnsupportedWebSocketVersionResponse(channel);
        }else{
            //Make a handshake with client,client will receive status:101 Switching Protocols
            handshaker.handshake(channel,request);
        }
    }


    private void processWebsocketRequest(ChannelHandlerContext ctx, WebSocketFrame request) throws Exception{
        Channel channel = ctx.getChannel();
        if(request instanceof CloseWebSocketFrame){
            DynamicMessage.removeAudiences(channel);
            handshaker.close(channel,(CloseWebSocketFrame)request);
        }else if(request instanceof PingWebSocketFrame){
            channel.write(new PongWebSocketFrame(request.getBinaryData()));
        }else if(request instanceof TextWebSocketFrame){
            TextWebSocketFrame txtReq = (TextWebSocketFrame)request;
            logger.info("TextRequest :{}" , txtReq.getText());
            if("disconnect".equalsIgnoreCase(txtReq.getText())){
                DynamicMessage.removeAudiences(channel);
                channel.close().sync();
                return;
            }
            String message = txtReq.getText();
            if(txtReq.getText().startsWith("#")){//connect first time
                message = message.substring(message.indexOf("#") + 1);
                DynamicMessage.addAudiences(channel);
            }
            DynamicMessage.radiate(message);
        }else {
            //other WebSocketFrame
        }
    }
}
