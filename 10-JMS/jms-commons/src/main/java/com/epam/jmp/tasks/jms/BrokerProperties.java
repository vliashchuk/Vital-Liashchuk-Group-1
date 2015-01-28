package com.epam.jmp.tasks.jms;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class BrokerProperties {
	
	private static final Logger LOGGER = Logger.getLogger(BrokerProperties.class);
	
	private String url;
	private String queue;
	private Integer threadsCount;
	
	public String getUrl() {
		return url;
	}
	public String getQueue() {
		return queue;
	}
	public Integer getThreadsCount() {
		return threadsCount;
	}
	
	public void load(){
		try {
			Properties brokerProperties = new Properties();
			brokerProperties.load(BrokerProperties.class.getClassLoader().getResourceAsStream("broker.properties"));
			url = brokerProperties.getProperty("broker.url");
			queue = brokerProperties.getProperty("queue.name");
			threadsCount = Integer.parseInt(brokerProperties.getProperty("threads.count"));
		} catch (IOException e) {
			LOGGER.error("Error while loading properties.", e);
			throw new RuntimeException(e);
		}
	}
}
