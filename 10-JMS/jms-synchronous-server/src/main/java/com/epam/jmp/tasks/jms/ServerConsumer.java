package com.epam.jmp.tasks.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.log4j.Logger;

public class ServerConsumer implements Task{

	private static final Logger LOGGER = Logger.getLogger(ServerConsumer.class);
	
	private ConnectionFactory connectionFactory;
	private String queueName;
	MessageListener messageListener;
	private boolean isRunning = false;

	public ServerConsumer(ConnectionFactory connectionFactory,
						  String queueName,
						  MessageListener messageListener) {
		super();
		this.connectionFactory = connectionFactory;
		this.queueName = queueName;
		this.messageListener = messageListener;
	}

	@Override
	public void run() {
		isRunning= true;
		Connection connection;
		
		try {
			connection = connectionFactory.createConnection();
		} catch (JMSException e) {
			isRunning = false;
			LOGGER.error("Error while creating connection", e);
			return;
		}		
		
		try {
			
			Session session = connection.createSession(true, -1);
			Destination masterQueue = session.createQueue(queueName);
			MessageConsumer consumer = session.createConsumer(masterQueue);
			
			connection.start();
			
			while(isRunning()){
				
				try {
					Message  request = consumer.receive();	
					messageListener.onMessage(request, session);		
					session.commit();
				} catch (JMSException e) {
					session.rollback();
					throw e;
				} catch (Exception e) {
					session.rollback();
					LOGGER.error("Error while handling message", e);
				}
				
			}
			connection.stop();
			connection.close();
			
		} catch (JMSException e) {
			isRunning = false;
			LOGGER.error("Error while running consumer", e);
			try {
				connection.close();
			} catch (JMSException e1) {
				LOGGER.error("Error while closing connection", e1);
			}
			
			throw new RuntimeException(e);
		}
	}

	public boolean isRunning() {
		return isRunning;
	}
	
	public void stop() {
		isRunning = false;
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
}
