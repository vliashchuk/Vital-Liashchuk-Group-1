package com.epam.jmp.tasks.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.log4j.Logger;

/**
 * @author Ihar_Karsakou
 *
 */
public class ServerConsumer implements Task {

    /**
     * LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ServerConsumer.class);

    /**
     * Connection Factory.
     */
    private ConnectionFactory connectionFactory;
    /**
     * Queue Name.
     */
    private String queueName;
    /**
     * Message Listener.
     */
    private MessageListener messageListener;
    /**
     * Is Running trigger.
     */
    private boolean isRunning = false;

    /**
     * @param connectionFactory connection factory to use
     * @param queueName queue name to use
     * @param messageListener message listener to use
     */
    public ServerConsumer(ConnectionFactory connectionFactory,
            String queueName, MessageListener messageListener) {
        super();
        this.connectionFactory = connectionFactory;
        this.queueName = queueName;
        this.messageListener = messageListener;
    }

    @Override
    public void run() {
        isRunning = true;
        Connection connection;

        try {
            connection = connectionFactory.createConnection();
        } catch (JMSException e) {
            isRunning = false;
            LOGGER.error("Error while creating connection", e);
            return;
        }

        try {

            Session session = connection.createSession(true, -1);
            Destination masterQueue = session.createQueue(queueName);
            MessageConsumer consumer = session.createConsumer(masterQueue);

            connection.start();

            while (isRunning()) {

                try {
                    Message request = consumer.receive();
                    messageListener.onMessage(request, session);
                    session.commit();
                } catch (JMSException e) {
                    session.rollback();
                    throw e;
                } catch (Exception e) {
                    session.rollback();
                    LOGGER.error("Error while handling message", e);
                }

            }
            connection.stop();
            connection.close();

        } catch (JMSException e) {
            isRunning = false;
            LOGGER.error("Error while running consumer", e);
            try {
                connection.close();
            } catch (JMSException e1) {
                LOGGER.error("Error while closing connection", e1);
            }

            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

}
