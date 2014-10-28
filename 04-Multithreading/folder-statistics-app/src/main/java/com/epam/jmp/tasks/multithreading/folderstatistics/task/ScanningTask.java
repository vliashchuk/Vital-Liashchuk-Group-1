package com.epam.jmp.tasks.multithreading.folderstatistics.task;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IFolderStatistics;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.IFolderStatisticsProvider;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.ITerminatable;

public class ScanningTask implements Runnable, ITerminatable, IFolderStatisticsProvider{

	private static final Logger LOGGER = Logger.getLogger(ScanningTask.class);
	
	
	FolderScanner folderScanner;

	
	public ScanningTask(Path folder){
		this.folderScanner = new FolderScanner(folder);
	}
	
	public boolean isRunning(){
		return folderScanner.isRunning();
	}
	
	@Override
	public void terminate(){
		folderScanner.terminate();
	}
	
	@Override
	public IFolderStatistics getFolderStatistics() {
		return folderScanner.getFolderStatistics();
	}
	
	@Override
	public void run() {
		
		try {
			folderScanner.scan();
			
		} catch (IOException  e) {
			LOGGER.error("Error while running ScanningTask.", e);
		}
	}

}
