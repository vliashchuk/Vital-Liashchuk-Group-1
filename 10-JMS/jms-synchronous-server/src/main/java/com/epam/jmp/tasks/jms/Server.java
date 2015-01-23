package com.epam.jmp.tasks.jms;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;


public class Server {
	
	private static final Logger LOGGER = Logger.getLogger(Server.class);
	
	private String brokerURL;
	private String queueName;
		
	public Server(String brokerURL, String queueName) {
		super();
		this.brokerURL = brokerURL;
		this.queueName = queueName;
	}

	public void run(){
		
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
		try {
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination masterQueue = session.createQueue(queueName);
			MessageConsumer consumer = session.createConsumer(masterQueue);
			ServerQueueListener serverQueueListener = new ServerQueueListener();
			serverQueueListener.setSession(session);
			consumer.setMessageListener(serverQueueListener);
			
		} catch (JMSException e) {
			LOGGER.error("Error while running server", e);
			throw new RuntimeException(e);
		}
		
	}
	
}
