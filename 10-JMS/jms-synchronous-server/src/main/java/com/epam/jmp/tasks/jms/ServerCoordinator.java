package com.epam.jmp.tasks.jms;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

public class ServerCoordinator implements Runnable{

	private static final Logger LOGGER = Logger.getLogger(ServerConsumer.class);
	
	private static final Integer CHECK_INTERVAL_IN_MS = new Integer(1000);
	
	private String brokerURL;
	private String queueName;
	private int threadsCount = 1;
	private boolean isRunning = false;
	
	public ServerCoordinator(String brokerURL, String queueName,
			int threadsCount) {
		super();
		this.brokerURL = brokerURL;
		this.queueName = queueName;
		if(threadsCount>0){
			this.threadsCount = threadsCount;			
		}
	}



	@Override
	public void run() {
		isRunning = true;
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
		
		RequestHandler handler = new RequestHandler();
		
		List<ServerConsumer> consumers = new ArrayList<>();
		
		while(isRunning()){
			
			List<ServerConsumer> runningConsumers = new ArrayList<>();
			for(ServerConsumer consumer:consumers){
				if(consumer.isRunning()){
					runningConsumers.add(consumer);
				}
			}
			
			if(runningConsumers.size()<threadsCount){
				int toCreate = threadsCount - runningConsumers.size();
				
				for(int i=0; i<toCreate; i++){
					
					ServerConsumer consumer =
							new ServerConsumer(connectionFactory, queueName, handler);
					runningConsumers.add(consumer);
					new Thread(consumer).start();
				}
				
				consumers = runningConsumers;
			}
			try {
				Thread.sleep(CHECK_INTERVAL_IN_MS);
			} catch (InterruptedException e) {
				stop();
			}
		}
		
		for(ServerConsumer consumer:consumers){
			consumer.stop();
		}
		
	}

	public boolean isRunning() {
		return isRunning;
	}
	
	public void stop() {
		isRunning = false;
		
	}
	
}
