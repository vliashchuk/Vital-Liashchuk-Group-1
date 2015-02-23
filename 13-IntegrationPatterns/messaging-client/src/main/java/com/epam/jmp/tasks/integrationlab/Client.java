package com.epam.jmp.tasks.integrationlab;

public abstract class Client {

	public static final String QUEUE_NAME =
			"com.epam.jmp.tasks.integrationlab.SampleQueue";
	
	protected ConnectionFactory connectionFactory;

	public Client(ConnectionFactory connectionFactory) {
		super();
		this.connectionFactory = connectionFactory;
	}

	public abstract void start();
	
}
