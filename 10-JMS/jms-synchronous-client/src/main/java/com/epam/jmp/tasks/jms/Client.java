package com.epam.jmp.tasks.jms;

/**
 * @author Ihar_Karsakou
 *
 */
public class Client {


    /**
     * clientProducerRunner.
     */
    private TaskRunner clientProducerRunner;

    /**
     * Stops client.
     */
    public void stop() {
        clientProducerRunner.stop();
    }

    /**
     * Starts client.
     */
    public void start() {
        BrokerProperties properties = new BrokerProperties();
        properties.load();

        TaskFactory clientProducerFactory = new ClientProducerFactory(properties.getUrl(),
                properties.getQueue());

        clientProducerRunner = new TaskRunner(clientProducerFactory);
        clientProducerRunner.setThreadsCount(properties.getThreadsCount());

        new Thread(clientProducerRunner, clientProducerRunner.getName()).start();
    }

}
