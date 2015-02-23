package com.epam.jmp.tasks.integrationlab;

import org.apache.log4j.Logger;

public class Producer extends Client {

	private static final Logger LOGGER = Logger.getLogger(Producer.class);
	
	public Producer(ConnectionFactory connectionFactory) {
		super(connectionFactory);
	}

	@Override
	public void start() {
		InboundChannelAdapter adapter = new InboundChannelAdapter();
		adapter.setConnectionFactory(connectionFactory);
		adapter.setDefaulQueueName(QUEUE_NAME);
		
		while(true){
			adapter.send(new IMessageCreator() {
				
				@Override
				public IMessage create() {
					IMessage m = new Message();
					m.setBody(new StringBuffer("Hello from ")
							  .append(this.getClass().getName())
							  .append(" [")
							  .append(Thread.currentThread().getId())
							  .append("]")
							  .toString());
					return m;
				}
			});
			
			LOGGER.debug(new StringBuffer(this.getClass().getName())
						 .append(" [")
						 .append(Thread.currentThread().getId())
						 .append("]")
						 .append(" has send message.")
					     .toString());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				LOGGER.error("Error while slipping.", e);
			}
		}
	}


}
