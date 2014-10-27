package com.epam.jmp.tasks.multithreading.folderstatistics.core;

public interface IProgressPuller {
	
	/**
	 * @param progress progress
	 */
	IScanningProgerss pollProgress() throws ProgressWrittingError;
}
