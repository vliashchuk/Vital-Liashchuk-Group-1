package com.epam.jmp.tasks.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author Ihar_Karsakou
 *
 */
public class ServerConsumerFactory implements TaskFactory {

    /**
     *  Queue Name.
     */
    private String queueName;
    /**
     * Connection Factory.
     */
    private ActiveMQConnectionFactory connectionFactory;
    /**
     * Message Listener.
     */
    private MessageListener messageListener;

    /**
     * @param brokerURL broker URL to use
     * @param queueName queue name to use
     */
    public ServerConsumerFactory(String brokerURL, String queueName) {
        super();
        this.queueName = queueName;

        connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        messageListener = new MessageListener(new RequestHandler());
    }

    @Override
    public Task createTask() {

        return new ServerConsumer(connectionFactory, queueName, messageListener);

    }

}
