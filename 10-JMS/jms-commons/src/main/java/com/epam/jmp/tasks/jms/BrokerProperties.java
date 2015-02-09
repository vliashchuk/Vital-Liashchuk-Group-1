package com.epam.jmp.tasks.jms;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author Ihar_Karsakou
 *
 */
public class BrokerProperties {

    /**
     * LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(BrokerProperties.class);

    /**
     * Url string.
     */
    private String url;
    /**
     * Queue name.
     */
    private String queue;
    /**
     * Threads count.
     */
    private Integer threadsCount;

    /**
     * @return Url string
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return Queue name
     */
    public String getQueue() {
        return queue;
    }

    /**
     * @return Threads count
     */
    public Integer getThreadsCount() {
        return threadsCount;
    }

    /**
     * Loads properties from broker.properties file.
     */
    public void load() {
        try {
            Properties brokerProperties = new Properties();
            brokerProperties.load(BrokerProperties.class.getClassLoader()
                    .getResourceAsStream("broker.properties"));
            url = brokerProperties.getProperty("broker.url");
            queue = brokerProperties.getProperty("queue.name");
            threadsCount = Integer.parseInt(brokerProperties
                    .getProperty("threads.count"));
        } catch (IOException e) {
            LOGGER.error("Error while loading properties.", e);
            throw new RuntimeException(e);
        }
    }
}
