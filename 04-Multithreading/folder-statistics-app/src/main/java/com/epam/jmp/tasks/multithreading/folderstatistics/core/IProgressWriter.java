package com.epam.jmp.tasks.multithreading.folderstatistics.core;

public interface IProgressWriter {

	/**
	 * @param progress progress
	 */
	void writeProgress(IScanningProgerss progerss) throws ProgressWrittingError;
	
}
