package com.epam.jmp.tasks.integrationlab;

import org.apache.log4j.Logger;

public class Consumer extends Client {

	private static final Logger LOGGER = Logger.getLogger(Producer.class);
	
	public Consumer(ConnectionFactory connectionFactory) {
		super(connectionFactory);
	}

	@Override
	public void start() {
		OutboundChannelAdapter adapter = new OutboundChannelAdapter();
		adapter.setConnectionFactory(connectionFactory);
		adapter.setDefaulQueueName(QUEUE_NAME);
		
		while(true){
			
			IMessage message = adapter.receive();
			
			LOGGER.debug(new StringBuffer(this.getClass().getName())
						 .append(" [")
						 .append(Thread.currentThread().getId())
						 .append("]")
						 .append(" has received message: ")
						 .append(message.getBody())
					     .toString());
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				LOGGER.error("Error while slipping.", e);
			}

			
		}
	}


}
