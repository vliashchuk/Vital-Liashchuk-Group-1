package com.epam.jmp.tasks.multithreading.folderstatistics.task;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IFolderStatisticsProvider;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.ITerminatable;

public class DetailViewDrawingTask implements Runnable, ITerminatable{

	private static final Logger LOGGER = Logger.getLogger(DetailViewDrawingTask.class);
	
	private static final int DEFAULT_OUTPUT_TIMEOUT_IN_MS = 1000;
	
	private volatile boolean running = true;
	
	private IFolderStatisticsProvider folderStatisticsProvider;
	private ScanningTaskDetailView view;
	private int outputTimeout;
	
	public DetailViewDrawingTask(ScanningTaskDetailView view){
		this.view = view;
		this.outputTimeout = DEFAULT_OUTPUT_TIMEOUT_IN_MS;
	}
	
	public DetailViewDrawingTask(ScanningTaskDetailView view, int outputTimeout){
		this.view = view;
		this.outputTimeout = outputTimeout;
	}

	
	public void setFolderStatisticsAware(IFolderStatisticsProvider folderStatisticsProvider){
		this.folderStatisticsProvider = folderStatisticsProvider;
	}
	
	@Override
	public void terminate() {
		running = false;
	}

	@Override
	public void run() {
		try {
			
			while(running){
				view.setFolderStatistics(folderStatisticsProvider.getFolderStatistics());
				view.draw();
				Thread.sleep(outputTimeout);
	
			}
		} catch (InterruptedException e) {
			LOGGER.error("Interrupted while drawing detail view.", e);
		}
		
		
	}

}
