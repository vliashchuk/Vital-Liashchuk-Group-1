package com.epam.jmp.tasks.jms;

public interface Task extends Runnable{

	boolean isRunning();
	
	void stop();
	
	String getName();
	
}
