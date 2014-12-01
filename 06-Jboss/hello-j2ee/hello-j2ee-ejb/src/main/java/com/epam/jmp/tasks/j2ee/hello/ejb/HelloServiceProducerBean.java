package com.epam.jmp.tasks.j2ee.hello.ejb;

import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

@Stateless
public class HelloServiceProducerBean implements HelloServiceProducer, HelloServiceProducerRemote{

	private static final Logger LOGGER = Logger.getLogger(HelloServiceProducerBean.class);
	
    public final static String CONNECTION_FACTORY_NAME = "/ConnectionFactory";

    //*************** Connection Factory JNDI name *************************
    public final static String QUEUE_NAME = "queue/HelloQueue";

	@Override
	public void hello(String message) {
		try {
			InitialContext context = new InitialContext();
			QueueConnectionFactory connectionFactory =
					(QueueConnectionFactory) context.lookup(CONNECTION_FACTORY_NAME);
			QueueConnection connection = connectionFactory.createQueueConnection();
			QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			connection.start();
			Queue queue = (Queue) context.lookup(QUEUE_NAME);
			QueueSender sender = session.createSender(queue);
			
			TextMessage msg = session.createTextMessage();
			msg.setText(message);
			sender.send(msg);  // Messages sent
		} catch (NamingException | JMSException e) {
			LOGGER.error("Error while sending message to queue.", e);
			throw new RuntimeException("Error while sending message to queue.", e);
		}

        
	}
	

}
