package com.epam.jmp.tasks.multithreading.folderstatistics.scanner;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IFolderStatisticsProvider;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.IJobProperties;
import com.epam.jmp.tasks.multithreading.folderstatistics.task.ScanningTask;

public class ScanningJob implements IJobProperties{
	String name;
	Thread thread;
	ScanningTask task;
	
	public ScanningJob(String name, Thread thread, ScanningTask task){
		this.name = name;
		this.thread = thread;
		this.task = task;
	}
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public boolean isActive(){
		return thread.isAlive();
	}
	
	public void stop() throws InterruptedException{
		if(isActive()){
			task.terminate();
			thread.join();

		}
	}
	
	public IFolderStatisticsProvider getFolderStatisticsProvider(){
		return task;
	}

}