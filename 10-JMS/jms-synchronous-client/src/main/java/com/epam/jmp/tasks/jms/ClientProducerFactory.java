package com.epam.jmp.tasks.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ClientProducerFactory implements TaskFactory {

	private ActiveMQConnectionFactory connectionFactory;
	private String queueName;
		
	public ClientProducerFactory(String brokerURL, String queueName) {
		super();
		this.connectionFactory = new ActiveMQConnectionFactory(brokerURL);
		this.queueName = queueName;
	}

	
	@Override
	public Task createTask() {
		return new ClientProducer(connectionFactory, queueName);
	}

}
