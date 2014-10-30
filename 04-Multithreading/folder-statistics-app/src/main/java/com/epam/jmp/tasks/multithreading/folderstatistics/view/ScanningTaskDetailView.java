package com.epam.jmp.tasks.multithreading.folderstatistics.view;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IFolderStatistics;

public class ScanningTaskDetailView {

	private static final Logger LOGGER = Logger.getLogger(ScanningTaskDetailView.class);
	
	IFolderStatistics folderStatistics;
	
	public void setFolderStatistics(IFolderStatistics folderStatistics){
		this.folderStatistics = folderStatistics;
	}
	
	public void draw(){
		LOGGER.info("FOLDER STATISTICS:");
		LOGGER.info("Path        : " + folderStatistics.getFolderPath().toString());
		LOGGER.info("File count  : " + folderStatistics.getFileCount());
		LOGGER.info("Folder count: " + folderStatistics.getFolderCount());
		LOGGER.info("Size        : " + folderStatistics.getSize());
	}
}
