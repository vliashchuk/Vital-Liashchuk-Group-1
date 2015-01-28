package com.epam.jmp.tasks.jms;

import java.io.IOException;
import java.util.Properties;

import org.apache.activemq.broker.BrokerService;
import org.apache.log4j.Logger;


public class Server {
	
	private static final Logger LOGGER = Logger.getLogger(Server.class);
	
	private BrokerService broker;
	TaskRunner serverConsumerRunner;
	public Server() {
		super();
	}

	public void start(){
		
		BrokerProperties properties = new BrokerProperties();
		properties.load();
		
		try {
	        broker = new BrokerService();
	        broker.setPersistent(false);
	        broker.setUseJmx(false);
	        broker.addConnector(properties.getUrl());
	        broker.start();
	        
		} catch (Exception e) {
			LOGGER.error("Error while starting broker.", e);
			throw new RuntimeException(e);
		}

		TaskFactory serverConsumerFactory =
				new ServerConsumerFactory(properties.getUrl(), properties.getQueue());
		
		TaskRunner serverConsumerRunner = new TaskRunner(serverConsumerFactory);
		serverConsumerRunner.setThreadsCount(properties.getThreadsCount());
		
		new Thread(serverConsumerRunner, serverConsumerRunner.getName()).start();
	}
	
	public void stop(){
		serverConsumerRunner.stop();
		
		try {
			broker.stop();
		} catch (Exception e) {
			LOGGER.error("Error while stopping broker.", e);
		}
		
		
	}
	
}
