package com.epam.jmp.tasks.multithreading.folderstatistics.task;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IFolderStatistics;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.IScanningTaskListener;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.IScanningTaskState;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.ITerminatable;
import com.epam.jmp.tasks.multithreading.folderstatistics.scanner.FolderScanner;
import com.epam.jmp.tasks.multithreading.folderstatistics.scanner.TaskStatus;

public class ScanningTask implements Runnable, ITerminatable{

	private static final Logger LOGGER = Logger.getLogger(ScanningTask.class);
	
	private String jobName;
	private FolderScanner folderScanner;
	private Collection<IScanningTaskListener> listeners;
	private TaskStatus status = TaskStatus.SUBMITED;
	private boolean terminated = false;

	
	public ScanningTask(Path folder, Collection<IScanningTaskListener> listeners){
		this.folderScanner = new FolderScanner(folder);
		jobName = "Job for " + folder.toString();
		if(listeners!= null){
			this.listeners = listeners;
		} else {
			listeners = new ArrayList<IScanningTaskListener>();
		}
	}
	
	@Override
	public boolean isRunning(){
		return folderScanner.isRunning();
	}
	
	@Override
	public void terminate(){
		terminated = true;
		folderScanner.terminate();
	}
	
	@Override
	public void run() {
		
		try {
			status = TaskStatus.RUNNING;
			
			for(IScanningTaskListener listener:listeners){
				listener.onStateChanged(getTaskState());
			}
			
			folderScanner.scan();
			
			if(terminated){
				status = TaskStatus.CANCELLED;
			} else {
				status = TaskStatus.COMPLETED;
			}
			
		} catch (IOException  e) {
			status = TaskStatus.FAILED;
			LOGGER.error("Error while running ScanningTask.", e);
		} finally{
			for(IScanningTaskListener listener:listeners){
				listener.onStateChanged(getTaskState());
			}
		}
	}
	
	public IScanningTaskState getTaskState(){
		return new JobProperties(jobName, status, folderScanner.getFolderStatistics());
	}
	
	private class JobProperties implements IScanningTaskState{

		private String name;
		private TaskStatus status;
		private IFolderStatistics statistics;
		
		
		public JobProperties(String jobName, TaskStatus status, IFolderStatistics folderStatistics){
			this.name = jobName;
			this.status = status;
			this.statistics = folderStatistics;
		}
		
		@Override
		public String getName() {
			return name;
		}

		@Override
		public IFolderStatistics getFolderStatistics() {
			return statistics;
		}

		@Override
		public TaskStatus getStatus() {
			return status;
		}
		
	}

}
