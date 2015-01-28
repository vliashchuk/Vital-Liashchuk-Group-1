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
		runSpringImplementation();		
//		if(args != null && args.length>0 && args[0].equals("spring")){
//			runSpringImplementation();
//		} else {
//			runJavaImplementation();
//		}
		
	}
	
	private static void runSpringImplementation(){
		ApplicationContext context = new ClassPathXmlApplicationContext("server-context.xml");
	}
	
	private static void runJavaImplementation(){
		Server server = new Server();
		server.start();
	}

}
