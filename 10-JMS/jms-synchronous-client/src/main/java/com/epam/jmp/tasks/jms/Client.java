package com.epam.jmp.tasks.jms;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

public class Client {
	
	private static final Logger LOGGER = Logger.getLogger(Client.class);
	
	private String brokerURL;
	private String queueName;
		
	public Client(String brokerURL, String queueName) {
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
			
			MessageProducer producer = session.createProducer(masterQueue);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			Destination resposeQueue = session.createTemporaryQueue();
            MessageConsumer consumer = session.createConsumer(resposeQueue);
			
            int i = 0;
            while(true){
            	try {
	        		//imitate preparing
	                try {
	        			Thread.sleep(1000);
	        		} catch (InterruptedException e) {
	        			e.printStackTrace();
	        			throw new RuntimeException(e);
	        		}
	            	
	            	i++;
	            	TextMessage txtMessage = session.createTextMessage();
	            	txtMessage.setText("Client " + i);
	                txtMessage.setJMSReplyTo(resposeQueue);
	
	                LOGGER.info("Send: " + txtMessage.getText());
	                producer.send(txtMessage);
	                
	                Message response = consumer.receive();
	                if (response instanceof TextMessage) {
	                	LOGGER.info("Recive: " + ((TextMessage)response).getText());
	                } else {
	                	LOGGER.error("Recive: not text message!");
	                }
        		} catch (JMSException e) {
        			LOGGER.error("Error while sending message", e);
        		}

            }
            
		} catch (JMSException e) {
			LOGGER.error("Error while running client", e);
			throw new RuntimeException(e);
		}
		
	}
	
}
