package com.epam.jmp.tasks.jms;

import org.apache.log4j.Logger;

public class Client {
	
//	private static final Logger LOGGER = Logger.getLogger(Client.class);
	
	TaskRunner clientProducerRunner;
	
	public void stop(){
		clientProducerRunner.stop();
	}
	
	public void start(){
		BrokerProperties properties = new BrokerProperties();
		properties.load();
		
		TaskFactory clientProducerFactory =
				new ClientProducerFactory(properties.getUrl(), properties.getQueue());
		
		clientProducerRunner = new TaskRunner(clientProducerFactory);
		clientProducerRunner.setThreadsCount(properties.getThreadsCount());
		
		new Thread(clientProducerRunner, clientProducerRunner.getName()).start();
		
//		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
//		try {
//			Connection connection = connectionFactory.createConnection();
//			connection.start();
//			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//			Destination masterQueue = session.createQueue(queueName);
//			
//			MessageProducer producer = session.createProducer(masterQueue);
//			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//			
//			Destination resposeQueue = session.createTemporaryQueue();
//            MessageConsumer consumer = session.createConsumer(resposeQueue);
//			
//            int i = 0;
//            while(true){
//            	try {
//	        		//imitate preparing
//	                try {
//	        			Thread.sleep(1000);
//	        		} catch (InterruptedException e) {
//	        			e.printStackTrace();
//	        			throw new RuntimeException(e);
//	        		}
//	            	
//	            	i++;
//	            	TextMessage txtMessage = session.createTextMessage();
//	            	txtMessage.setText("Client " + i);
//	                txtMessage.setJMSReplyTo(resposeQueue);
//	
//	                LOGGER.info("Send: " + txtMessage.getText());
//	                producer.send(txtMessage);
//	                
//	                Message response = consumer.receive();
//	                if (response instanceof TextMessage) {
//	                	LOGGER.info("Recive: " + ((TextMessage)response).getText());
//	                } else {
//	                	LOGGER.error("Recive: not text message!");
//	                }
//        		} catch (JMSException e) {
//        			LOGGER.error("Error while sending message", e);
//        		}
//
//            }
//            
//		} catch (JMSException e) {
//			LOGGER.error("Error while running client", e);
//			throw new RuntimeException(e);
//		}
		
	}
	
}
