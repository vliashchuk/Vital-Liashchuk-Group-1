package com.epam.jmp.tasks.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;

public class MessageListener implements SessionAwareMessageListener<Message>{

	private RequestHandler requestHandler;

	public MessageListener() {
		super();
		this.requestHandler = new RequestHandler();
	}

	@Override
	public void onMessage(Message request, Session session) throws JMSException {
		
		//imitate handling
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		if(request.getJMSReplyTo() == null){
			throw new RuntimeException(
					"Response queue for request message not specified: " + 
							request);
		}
		
		String requestString;
		if(request instanceof TextMessage){
			requestString = ((TextMessage)request).getText();
		} else {
			requestString = "Not recognized message";
		}

		String responseString = requestHandler.handle(requestString);

		MessageProducer producer = session.createProducer(null);
		producer.send(request.getJMSReplyTo(),
					  session.createTextMessage(responseString));
		
	}
	
	
}
