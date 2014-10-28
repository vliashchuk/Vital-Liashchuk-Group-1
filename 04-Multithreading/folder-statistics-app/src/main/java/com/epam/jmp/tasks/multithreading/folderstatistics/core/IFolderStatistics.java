package com.epam.jmp.tasks.multithreading.folderstatistics.core;

import java.nio.file.Path;

public interface IFolderStatistics {

	/**
	 * @return folder path
	 */
	Path getFolderPath();
	
	/**
	 * @return number of files.
	 */
	Integer getFileCount();
	/**
	 * @return number of folders
	 */
	Integer getFolderCount();
	/**
	 * @return folder size: sum of all files size in Bytes
	 */
	Long getSize();
	
}
