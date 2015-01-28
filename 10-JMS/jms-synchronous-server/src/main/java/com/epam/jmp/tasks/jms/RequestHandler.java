package com.epam.jmp.tasks.jms;

public class RequestHandler {

	public String handle(String request){
		return new StringBuffer()
			.append("Hello, ")
			.append(request)
			.append(". It's ")
			.append(Thread.currentThread().getName())
			.append(" ")
			.append(Thread.currentThread().getId()).toString();
	}
	
}
