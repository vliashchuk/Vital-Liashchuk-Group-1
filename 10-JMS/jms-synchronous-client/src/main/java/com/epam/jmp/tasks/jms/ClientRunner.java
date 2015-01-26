package com.epam.jmp.tasks.jms;

import java.util.Properties;

import org.apache.log4j.Logger;

public class ClientRunner {

	private static final Logger LOGGER = Logger.getLogger(ClientRunner.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		runJavaImplementation();
	}

	private static void runJavaImplementation(){
		Properties brokerProperties = new Properties();
		
		try {
			brokerProperties.load(ClientRunner.class.getClassLoader().getResourceAsStream("broker.properties"));
			Client server = new Client(brokerProperties.getProperty("broker.url"),
					   				   brokerProperties.getProperty("queue.name"));
			server.run();
	        
		} catch (Exception e) {
			LOGGER.error("Error while starting client.", e);
		}

	}
	
}
