package com.epam.jmp.tasks.multithreading.folderstatistics.progress;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IProgressWriter;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.IScanningProgerss;

public class LogProgressWriter implements IProgressWriter{

	private static final Logger LOGGER = Logger.getLogger(LogProgressWriter.class);
	
	@Override
	public void writeProgress(IScanningProgerss progerss) {
		if(!progerss.isComplete()){
			LOGGER.info("Scanning progress for '" + progerss.getFolderPath() + "':");
			LOGGER.info(progerss.getProgress());
		} else {
			LOGGER.info("FOLDER STATISTICS:");
			LOGGER.info("Path        : " + progerss.getFolderPath().toString());
			LOGGER.info("File count  : " + progerss.getStatistics().getFileCount());
			LOGGER.info("Folder count: " + progerss.getStatistics().getFolderCount());
			LOGGER.info("Size        : " + progerss.getStatistics().getSize());
		}
	}
	
}
