package com.epam.jmp.tasks.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

public class ServerConsumer implements Runnable{

	private static final Logger LOGGER = Logger.getLogger(ServerConsumer.class);
	
	private ConnectionFactory connectionFactory;
	private String queueName;
	RequestHandler handler;
	private boolean isRunning = false;

	public ServerConsumer(ConnectionFactory connectionFactory, String queueName, RequestHandler handler) {
		super();
		this.connectionFactory = connectionFactory;
		this.queueName = queueName;
		this.handler = handler;
	}

	@Override
	public void run() {
		isRunning= true;
		Connection connection;
		
		try {
			connection = connectionFactory.createConnection();
		} catch (Exception e) {
			isRunning = false;
			LOGGER.error("Error while creating connection", e);
			return;
		}		
		
		try {
			
			Session session = connection.createSession(true, -1);
			Destination masterQueue = session.createQueue(queueName);
			MessageConsumer consumer = session.createConsumer(masterQueue);
			MessageProducer producer = session.createProducer(null);
			
			while(isRunning()){
				
				try {
					Message  request = consumer.receive();
					
					if(request.getJMSReplyTo() == null){
						throw new RuntimeException("Response queue for request message not specified");
					}
					
					String requestString;
					if(request instanceof TextMessage){
						requestString = ((TextMessage)request).getText();
					} else {
						requestString = "Not recognized message";
					}

					String responseString = handler.handle(requestString);

					producer.send(request.getJMSReplyTo(),
								  session.createTextMessage(responseString));
					
					session.commit();
				} catch (ReturnPathNotSpecifiedException e) {
					session.commit();
					throw e;
				} catch (JMSException e) {
					session.rollback();
					throw e;
				}
				
			}

			
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
	
}
