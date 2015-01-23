package com.epam.jmp.tasks.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.listener.SessionAwareMessageListener;

public class ServerQueueListenerAdapter implements SessionAwareMessageListener<Message>{

	ServerQueueListener listener;
	
	public ServerQueueListenerAdapter(ServerQueueListener listener){
		this.listener = listener;
	}

	@Override
	public void onMessage(Message message, Session session) throws JMSException {
		listener.setSession(session);
		listener.onMessage(message);
	}
	
}
