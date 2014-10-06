package com.epam.jmp.tasks.classloader.shell;

public interface Command {

	void name();
	
	void execute(String[] parameters);
	
	void usage();
}
