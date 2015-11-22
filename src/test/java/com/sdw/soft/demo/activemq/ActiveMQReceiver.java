/**
 * @author shangyd
 * @Date 2015年11月21日 下午6:11:32
 * Copyright (c) 2015
 **/
package com.sdw.soft.demo.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class ActiveMQReceiver {

	private static final String BROKER_URL = "failover:(tcp://192.168.183.128:61616,tcp://192.168.183.129:61616,tcp://192.168.183.130:61616)?initialReconnectDelay=1000";
	
	@Test
	public void test01(){
		Connection connection = null;
		Session session = null;
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("testQueue");
			MessageConsumer consumer = session.createConsumer(destination);
			while(true){
				TextMessage message = (TextMessage)consumer.receive(1000);
				if(message != null)
					System.out.println("接收到消息:" + message.getText());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				session.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
}
