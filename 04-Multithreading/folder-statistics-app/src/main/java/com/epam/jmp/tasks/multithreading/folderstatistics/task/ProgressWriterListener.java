package com.epam.jmp.tasks.multithreading.folderstatistics.task;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IProgressPuller;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.IProgressWriter;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.IScanningProgerss;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.ITerminatable;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.ProgressWrittingError;

public class ProgressWriterListener implements Runnable, ITerminatable {

	private static final Logger LOGGER = Logger.getLogger(ProgressWriterListener.class);
	
	private volatile boolean running = true;
	
	IProgressPuller puller;
	IProgressWriter writer;
	
	public ProgressWriterListener(IProgressPuller puller, IProgressWriter writer){
		this.puller = puller;
		this.writer = writer;
	}
	
	public boolean isRunning(){
		return running;
	}
	@Override
	public void terminate(){
		running = false;
	}
	
	@Override
	public void run() {
		while(isRunning()){
			try {
				IScanningProgerss progress = puller.pollProgress();
				if(progress!=null){
					writer.writeProgress(progress);
				}
				
			} catch (ProgressWrittingError e) {
				LOGGER.error(e);
			}
		}
	}

}
