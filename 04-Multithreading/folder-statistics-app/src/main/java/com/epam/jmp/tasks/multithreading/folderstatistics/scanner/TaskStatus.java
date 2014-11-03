package com.epam.jmp.tasks.multithreading.folderstatistics.scanner;

public enum TaskStatus {

	SUBMITED("Submited"),
	RUNNING("Running"),
	COMPLETED("Completed"),
	CANCELLED("Cancelled"),
	FAILED("Failed");
	
	private TaskStatus(String name){
		this.name = name;
	}
	private String name;
	
	@Override
	public String toString(){
		return name;
	}
}
