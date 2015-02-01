package com.epam.jmp.tasks.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.mockito.InOrder;
import org.mockito.Mockito;
import org.testng.annotations.Test;

public class MessageListenerTest {

    
    
    @Test
    public void test() throws JMSException{
        
        RequestHandler handler = Mockito.mock(RequestHandler.class);
        Mockito.when(handler.handle(Mockito.anyString())).thenReturn("response");

        MessageProducer producer = Mockito.mock(MessageProducer.class);       
        Session session = Mockito.mock(Session.class);
        Mockito.when(session.createProducer(Mockito.any(Destination.class))).thenReturn(producer);
        
        TextMessage request =  Mockito.mock(TextMessage.class);
        Mockito.when(request.getText()).thenReturn("request");
        Mockito.when(request.getJMSReplyTo()).thenReturn(Mockito.mock(Destination.class));
        
        
        
        MessageListener listener = new MessageListener(handler);
        listener.onMessage(request, session);
        
        InOrder order = Mockito.inOrder(request, handler, producer, session);
        order.verify(request).getJMSReplyTo();
        order.verify(request).getText();
        order.verify(handler).handle("request");
        order.verify(session).createProducer(Mockito.any(Destination.class));
        order.verify(request).getJMSReplyTo();
        order.verify(session).createTextMessage(Mockito.anyString());
        order.verify(producer).send(Mockito.any(Destination.class), Mockito.any(Message.class));
        
    }
    
}
