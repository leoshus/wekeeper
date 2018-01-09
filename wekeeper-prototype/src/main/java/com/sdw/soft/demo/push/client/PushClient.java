package com.sdw.soft.demo.push.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import java.io.IOException;

/**
 * Created by shangyindong on 2018/1/9.
 */
@ClientEndpoint
public class PushClient {

    private static final Logger logger = LoggerFactory.getLogger(PushClient.class);

    private Session session;

    @OnOpen
    public void open(Session session) {
        logger.info("Client Websocket is opening...");
        this.session = session;
    }

    @OnMessage
    public void onMessage(String message) {
        logger.info("Receive Server message:" + message);
    }

    @OnClose
    public void onClose() {
        logger.info("Client Websocket closed.");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    public void send(String message) {
        this.session.getAsyncRemote().sendText(message);
    }

    public void close() {
        try {
            if (this.session.isOpen()) {
                this.session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
