package com.epam.jmp.tasks.multithreading.folderstatistics.core;

public interface IFolderStatistics {

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
