package com.epam.jmp.tasks.jms;


public class ClientRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		runJavaImplementation();
	}

	private static void runJavaImplementation(){
		Client client = new Client();
		client.start();
	}
	
}
