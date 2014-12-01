package com.epam.jmp.tasks.j2ee.hello.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

@MessageDriven(activationConfig =
{
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/HelloQueue")
})
public class HelloServiceConsumerBean implements MessageListener {

	private static final Logger LOGGER = Logger.getLogger(HelloServiceConsumerBean.class);
	
	@EJB
	HelloService helloServiceBean;
	
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			helloServiceBean.hello(textMessage.getText());
		} catch (JMSException e) {
			LOGGER.error("Error while getting text of queue message!", e);
			throw new RuntimeException("Error while getting text of queue message!", e);
		}
	}

}
