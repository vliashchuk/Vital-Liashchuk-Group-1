package com.epam.jmp.tasks.multithreading.folderstatistics.core;

public interface IFolderStatisticsScanner {

	/**
	 * Scans folders recursively and calculates statistics. 
	 * @param folderPath folder to scan
	 */
	void scan(String folderPath);
	
	/**
	 * Stops scanning passed folder
	 * @param folderPath folder to stop scanning
	 */
	void stopScanning(String folderPath);
	
	/**
	 * Stops scanning all folders.
	 */
	void exit();
}
