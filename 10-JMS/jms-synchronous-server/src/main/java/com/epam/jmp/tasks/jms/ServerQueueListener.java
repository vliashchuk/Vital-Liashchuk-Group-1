package com.epam.jmp.tasks.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

public class ServerQueueListener implements MessageListener{

	private static final Logger LOGGER = Logger.getLogger(ServerQueueListener.class);
	
	private Session session;
	private MessageProducer producer; 

	
	public void setSession(Session session){
		this.session = session;
		try {
			this.producer = session.createProducer(null);
		} catch (JMSException e) {
			LOGGER.error("Error while creation Server listener fo master queue.", e);
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void onMessage(Message request) {
		
		//imitate handling
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		try {
			String requestString;
			if(request instanceof TextMessage){
				requestString = ((TextMessage)request).getText();
			} else {
				requestString = "Not recognized message";
			}
			
			Message response = session.createTextMessage("Hello, " + requestString);
			producer.send(request.getJMSReplyTo(), response);
			
		} catch (JMSException e) {
			LOGGER.error("Error while responsing on request message.", e);
		}
		

	}

}
