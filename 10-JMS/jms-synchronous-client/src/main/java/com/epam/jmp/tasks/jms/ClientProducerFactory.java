package com.epam.jmp.tasks.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author Ihar_Karsakou
 *
 */
public class ClientProducerFactory implements TaskFactory {

    /**
     * Connection Factory.
     */
    private ActiveMQConnectionFactory connectionFactory;
    /**
     * Queue Name.
     */
    private String queueName;

    /**
     * @param brokerURL broker URL to use
     * @param queueName queue name to use
     */
    public ClientProducerFactory(String brokerURL, String queueName) {
        super();
        this.connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        this.queueName = queueName;
    }

    @Override
    public Task createTask() {
        return new ClientProducer(connectionFactory, queueName);
    }

}
