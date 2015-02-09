package com.epam.jmp.tasks.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

/**
 * @author Ihar_Karsakou
 *
 */
public class ClientProducer implements Task {

    /**
     * LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(Client.class);

    /**
     * SLEEP_INTERVAL.
     */
    private static final int SLEEP_INTERVAL = 1000;

    /**
     * Connection Factory.
     */
    private ConnectionFactory connectionFactory;
    /**
     *  Queue Name.
     */
    private String queueName;
    /**
     * Is Running.
     */
    private boolean isRunning = false;

    /**
     * @param connectionFactory connection factory to use
     * @param queueName queue name to use
     */
    public ClientProducer(ConnectionFactory connectionFactory, String queueName) {
        super();
        this.connectionFactory = connectionFactory;
        this.queueName = queueName;
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

            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Destination masterQueue = session.createQueue(queueName);
            MessageProducer producer = session.createProducer(masterQueue);

            Destination resposeQueue = session.createTemporaryQueue();
            MessageConsumer consumer = session.createConsumer(resposeQueue);

            connection.start();

            int i = 0;
            while (isRunning()) {

                try {
                    // imitate preparing
                    try {
                        Thread.sleep(SLEEP_INTERVAL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }

                    i++;
                    TextMessage txtMessage = session.createTextMessage();
                    txtMessage.setText(getName() + " "
                            + Thread.currentThread().getId());
                    txtMessage.setJMSReplyTo(resposeQueue);

                    LOGGER.info("Send" + i + ": " + txtMessage.getText());
                    producer.send(txtMessage);

                    Message response = consumer.receive();
                    if (response instanceof TextMessage) {
                        LOGGER.info("Recive" + i + ": "
                                + ((TextMessage) response).getText());
                    } else {
                        LOGGER.error("Recive" + i + ": not text message!");
                    }

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
            LOGGER.error("Error while running producer", e);
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
