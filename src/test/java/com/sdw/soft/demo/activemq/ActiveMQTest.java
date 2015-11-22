/**
 * @author shangyd
 * @Date 2015年11月20日 下午10:18:58
 * Copyright (c) 2015
 **/
package com.sdw.soft.demo.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class ActiveMQTest {

	private static final String brokerUrl="failover:(tcp://192.168.183.128:61616,tcp://192.168.183.129:61616,tcp://192.168.183.130:61616)?initialReconnectDelay=1000";
	@Test
	public void test01(){
		
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
			Connection connection = connectionFactory.createConnection();
			connection.start();
			/**
			 * // 第一个参数是否使用事务:当消息发送者向消息提供者（即消息代理）发送消息时，消息发送者等待消息代理的确认，没有回应则抛出异常，消息发送程序负责处理这个错误。
            // 第二个参数消息的确认模式：
            // AUTO_ACKNOWLEDGE ： 指定消息提供者在每次收到消息时自动发送确认。消息只向目标发送一次，但传输过程中可能因为错误而丢失消息。
            // CLIENT_ACKNOWLEDGE ： 由消息接收者确认收到消息，通过调用消息的acknowledge()方法（会通知消息提供者收到了消息）
            // DUPS_OK_ACKNOWLEDGE ： 指定消息提供者在消息接收者没有确认发送时重新发送消息（这种确认模式不在乎接收者收到重复的消息）。
			 */
			Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("testQueue");
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			Message message = session.createTextMessage("hello zookeepr ActiveMQ cluster");
			producer.send(message);
			session.commit();
			producer.close();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
