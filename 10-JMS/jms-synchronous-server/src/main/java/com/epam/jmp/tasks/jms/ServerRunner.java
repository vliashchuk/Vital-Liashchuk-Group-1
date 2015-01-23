package com.epam.jmp.tasks.jms;

import java.util.Properties;

import org.apache.activemq.broker.BrokerService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ServerRunner {

	private static final Logger LOGGER = Logger.getLogger(ServerRunner.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		runJavaImplementation();
	}
	
	private static void runSpringImplementation(){
		ApplicationContext context = new ClassPathXmlApplicationContext("server-context.xml");
	}
	
	private static void runJavaImplementation(){
		Properties brokerProperties = new Properties();
		
		try {
			brokerProperties.load(ServerRunner.class.getClassLoader().getResourceAsStream("broker.properties"));
	        BrokerService broker = new BrokerService();
	        broker.setPersistent(false);
	        broker.setUseJmx(false);
	        broker.addConnector(brokerProperties.getProperty("broker.url"));
	        broker.start();
	        
		} catch (Exception e) {
			LOGGER.error("Error while starting broker.", e);
			throw new RuntimeException(e);
		}

		Server server = new Server(brokerProperties.getProperty("broker.url"),
								   brokerProperties.getProperty("queue.name"));
		server.run();
	}

}
