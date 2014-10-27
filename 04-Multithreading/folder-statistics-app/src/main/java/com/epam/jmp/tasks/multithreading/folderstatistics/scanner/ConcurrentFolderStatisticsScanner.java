package com.epam.jmp.tasks.multithreading.folderstatistics.scanner;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IFolderStatisticsScanner;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.IProgressWriter;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.ITerminatable;
import com.epam.jmp.tasks.multithreading.folderstatistics.progress.ProgressContainer;
import com.epam.jmp.tasks.multithreading.folderstatistics.task.ProgressWriterListener;
import com.epam.jmp.tasks.multithreading.folderstatistics.task.ScanningTask;

public class ConcurrentFolderStatisticsScanner implements IFolderStatisticsScanner {

	private static final Logger LOGGER = Logger.getLogger(ScanningTask.class);
	
	private boolean exited = false;
	
	IProgressWriter outputWriter;
	ProgressContainer progressContainer;
	Job progressWriterJob;
	Map<String,Job> runningJobs = new HashMap<>();
	
	public ConcurrentFolderStatisticsScanner(IProgressWriter outputWriter){
		this.outputWriter = outputWriter;
		
		progressContainer = new ProgressContainer();
		ProgressWriterListener progressWriterListener = new ProgressWriterListener(
				progressContainer, outputWriter);
		Thread progressWriterThread = new Thread(progressWriterListener);
		progressWriterJob = new Job(progressWriterThread, progressWriterListener);
		progressWriterThread.start();
	}	
	
	@Override
	public void scan(String folderPath) {
		
		if(exited){
			LOGGER.error("Can't scan because scanner was exited!");
			return;
		}
		Path path = Paths.get(folderPath);
		String key = path.toString().toLowerCase();
		
		if(runningJobs.containsKey(key)){
			if(runningJobs.get(key).isActive()){
				LOGGER.error("Can't start scaning folder because it is in progress! ("
							  + path.toString() + ")");
				return;
			} else {
				runningJobs.remove(key);
			}
		}
		
		ScanningTask scanningTask = new ScanningTask(
				path,
				progressContainer,
				500);
		Thread scanningThread = new Thread(scanningTask);
		
		runningJobs.put(key,
						new Job(scanningThread, scanningTask));
		scanningThread.start();
		
		for(Job job:runningJobs.values()){
			if(!job.isActive()){
				runningJobs.remove(job);
			}
		}
	}

	/**
	 * We don't need this method.
	 * @param folderPath folder to stop scanning
	 */
	@Override
	public void stopScanning(String folderPath) {
		if(exited){
			LOGGER.error("Can't stop scaning because scanner was exited!");
			return;
		}

		String key = Paths.get(folderPath).toString().toLowerCase();
		if(runningJobs.containsKey(key)){
			runningJobs.get(key).stop();
			runningJobs.remove(key);
		}
		LOGGER.debug("folder stopped: " + folderPath);
	}

	@Override
	public void exit() {
		LOGGER.debug("Starting exit.");
		exited = true;
		progressWriterJob.stop();
		for(Job job:runningJobs.values()){
			job.stop();
		}
		runningJobs.clear();
		LOGGER.debug("Scanner Exited!");
	}
	
	private class Job {
		Thread thread;
		ITerminatable task;
		
		public Job(Thread thread, ITerminatable task){
			this.thread = thread;
			this.task = task;
		}
		
		public boolean isActive(){
			return thread.isAlive();
		}
		
		public void stop(){
			if(isActive()){
				task.terminate();
				try {
					thread.join();
				} catch (InterruptedException e) {
					LOGGER.error("Error while stopping thread!", e);
				}
			}
		}
		
	}

}
