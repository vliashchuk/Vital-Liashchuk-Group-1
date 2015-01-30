package com.epam.jmp.tasks.jms;

import org.apache.activemq.broker.BrokerService;
import org.apache.log4j.Logger;

/**
 * @author Ihar_Karsakou
 *
 */
public class Server {

    /**
     * LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(Server.class);

    /**
     *  Broker.
     */
    private BrokerService broker;
    /**
     *  Server Consumer Runner.
     */
    private TaskRunner serverConsumerRunner;

    /**
     * Default constructor.
     */
    public Server() {
        super();
    }

    /**
     * Starts server.
     */
    public void start() {

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

        TaskFactory serverConsumerFactory = new ServerConsumerFactory(
                properties.getUrl(), properties.getQueue());

        serverConsumerRunner = new TaskRunner(serverConsumerFactory);
        serverConsumerRunner.setThreadsCount(properties.getThreadsCount());

        new Thread(serverConsumerRunner, serverConsumerRunner.getName())
                .start();
    }

    /**
     * Stops server.
     */
    public void stop() {
        serverConsumerRunner.stop();

        try {
            broker.stop();
        } catch (Exception e) {
            LOGGER.error("Error while stopping broker.", e);
        }

    }

}
