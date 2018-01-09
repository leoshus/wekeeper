package com.sdw.soft.demo.push.client;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.net.URI;

/**
 * Created by shangyindong on 2018/1/9.
 */
public class TestPushClient {
    public static void main(String[] args) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            PushClient client = new PushClient();
            container.connectToServer(client, new URI("ws://localhost:8099/"));

            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
