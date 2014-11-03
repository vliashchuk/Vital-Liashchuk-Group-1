package com.epam.jmp.tasks.multithreading.folderstatistics.core;

public interface ITerminatable {

	boolean isRunning();
	void terminate();
}
