package com.epam.jmp.tasks.integrationlab;

interface IDestinationAwareMessage extends IMessage{

	String getQueueName();
	void setQueueName(String name);
	
}
