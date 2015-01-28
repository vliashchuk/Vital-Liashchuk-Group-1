package com.epam.jmp.tasks.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ServerConsumerFactory implements TaskFactory {

	private String queueName;
	
	ActiveMQConnectionFactory connectionFactory;
	MessageListener messageListener;
	
	public ServerConsumerFactory(String brokerURL, String queueName) {
		super();
		this.queueName = queueName;
		
		connectionFactory = new ActiveMQConnectionFactory(brokerURL);
		messageListener = new MessageListener();
	}
	
	@Override
	public Task createTask() {

		return new ServerConsumer(connectionFactory, queueName, messageListener);
		
	}

}
