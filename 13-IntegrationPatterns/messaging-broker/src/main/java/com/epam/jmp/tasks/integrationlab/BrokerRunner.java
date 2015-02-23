package com.epam.jmp.tasks.integrationlab;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class BrokerRunner 
{
    public static void main( String[] args ) throws IOException
    {
    	Integer port = Integer.parseInt(args[0]);
    	
    	Broker broker = new Broker(port);
    	broker.start();
    }
}
