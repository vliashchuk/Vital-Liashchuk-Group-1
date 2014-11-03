package com.epam.jmp.tasks.multithreading.folderstatistics.core;

public interface IFolderScannerApp {

	/**
	 * @return application name.
	 */
	String getName();
	
	/**
	 * Submits scanning task and represents it in list view.
	 * Task scans folders recursively and calculates statistics. 
	 * @param folderPath folder to scan
	 */
	void scan(String folderPath);
	
	/**
	 * Stops task that is scanning passed folder.
	 * Represents it in list view.
	 * @param folderPath folder to stop scanning
	 */
	void stopScanning(String folderPath);
	
	/**
	 * Exits FolderScannerApp.
	 * Stops scanning all folders.
	 * After this method is called other methods of this class have no effect.
 	 * @throws InterruptedException thrown when 
	 * thread is interrupted while waiting for stopping other threads.
	 */
	void exit() throws InterruptedException;
	
//	/**
//	 * Shows details of scanning for corresponding folder.
//	 */
//	void showDetails(String folderPath);
//	
//	/**
//	 * Shows list of all scanning tasks with statuses.
//	 */
//	void showList();
}
