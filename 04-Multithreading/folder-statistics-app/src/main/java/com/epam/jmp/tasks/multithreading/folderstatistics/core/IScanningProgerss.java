package com.epam.jmp.tasks.multithreading.folderstatistics.core;

import java.nio.file.Path;

public interface IScanningProgerss {
	/**
	 * @return path of folder.
	 */
	Path getFolderPath();
	
	/**
	 * @return String that represents Progress.
	 */
	String getProgress();
	
	/**
	 * @return true if scanning complete.
	 */
	boolean isComplete();
	
	/**
	 * returns not null only if isComplete=true
	 * @return folder statistics
	 */
	IFolderStatistics getStatistics();
	
}
