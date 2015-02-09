package com.epam.jmp.tasks.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;

/**
 * @author Ihar_Karsakou
 *
 */
public class MessageListener implements SessionAwareMessageListener<Message> {

    /**
     * SLEEP_INTERVAL.
     */
    private static final int SLEEP_INTERVAL = 1000;

    /**
     * Request Handler.
     */
    private RequestHandler requestHandler;

    /**
     * Default construnctor.
     *
     * @param requestHandler request handler to use.
     */
    public MessageListener(RequestHandler requestHandler) {
        super();
        this.requestHandler = requestHandler;
    }

    @Override
    public void onMessage(Message request, Session session) throws JMSException {

        // imitate handling
        try {
            Thread.sleep(SLEEP_INTERVAL);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (request.getJMSReplyTo() == null) {
            throw new RuntimeException(
                    "Response queue for request message not specified: "
                            + request);
        }

        String requestString;
        if (request instanceof TextMessage) {
            requestString = ((TextMessage) request).getText();
        } else {
            requestString = "Not recognized message";
        }

        String responseString = requestHandler.handle(requestString);

        MessageProducer producer = session.createProducer(null);
        producer.send(request.getJMSReplyTo(),
                session.createTextMessage(responseString));

    }

}
