package com.epam.jmp.tasks.multithreading.folderstatistics.task;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IPausable;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.ITerminatable;
import com.epam.jmp.tasks.multithreading.folderstatistics.task.utils.WaitNotify;

public abstract class AbstractDrawingTask<T> implements Runnable, ITerminatable, IPausable{

	private static final Logger LOGGER = Logger.getLogger(DetailViewDrawingTask.class);
	
	private static final int DEFAULT_OUTPUT_TIMEOUT_IN_MS = 1000;
	
	private volatile boolean running = true;
	
	private volatile boolean paused = false;
	
	private WaitNotify pauseWaitNotify = new WaitNotify();
	
	private int outputTimeout;
	
	/**
	 * delay between drawing output is set to default 1s.
	 */
	public AbstractDrawingTask(){
		this.outputTimeout = DEFAULT_OUTPUT_TIMEOUT_IN_MS;
	}
	
	/**
	 * @param outputTimeout delay between drawing output in ms.
	 */
	public AbstractDrawingTask(int outputTimeout){
		this.outputTimeout = outputTimeout;
	}

	/**
	 * Method draws output.
	 * This method takes last drawn object and returns currently drawn objects.
	 * It allows us to keep state of last drawn object is needed.  
	 * 
	 * @param lastDrawnObject
	 * @return currently drawn objects
	 */
	protected abstract T draw(T lastDrawnObject);
	
	@Override
	public void run() {
		try {
			
			T lastDrawnObject = null;
			
			while(running){
				
				if(paused){
					pauseWaitNotify.doWait();
				}
				
				lastDrawnObject = draw(lastDrawnObject);

				Thread.sleep(outputTimeout);
	
			}
		} catch (InterruptedException e) {
			LOGGER.error("Interrupted while drawing view in " + this.getClass(), e);
		}
		
		
	}

	@Override
	public void terminate() {
		running = false;
		pauseWaitNotify.doNotify();
	}
	
	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void resume() {
		paused = false;
		pauseWaitNotify.doNotify();
	}

}
