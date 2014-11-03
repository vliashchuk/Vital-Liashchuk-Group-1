package com.epam.jmp.tasks.multithreading.folderstatistics.task;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IScanningTaskListener;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.IScanningTaskState;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.ITerminatable;
import com.epam.jmp.tasks.multithreading.folderstatistics.view.ScanningTaskListView;


public class ListViewDrawingTask implements Runnable, ITerminatable, IScanningTaskListener{

	private static final Logger LOGGER = Logger.getLogger(ListViewDrawingTask.class);
	
	private volatile boolean running = true;
	
	private MonitorObject monitor = new MonitorObject();
	private boolean notifyed = false;
	
	private ScanningTaskListView view;
	
	private Map<String, IScanningTaskState> scanningJobs = new HashMap<>();
	
	public ListViewDrawingTask(ScanningTaskListView view){
		this.view = view;
	}

	private class MonitorObject{}
	
	@Override
	public void run() {
		try {
			
			while(true){
				
				IScanningTaskState[] jobsToDraw;
				synchronized (monitor) {
					while(!notifyed){
						monitor.wait();
					}
					notifyed = false;
					jobsToDraw = scanningJobs.values().toArray(
							new IScanningTaskState[scanningJobs.size()]);
				}
				if(running){
					view.setScanningJobs(jobsToDraw);
					view.draw();
				} else {
					break;
				}				
			}
		} catch (InterruptedException e) {
			LOGGER.error("Interrupted while drawing view in " + this.getClass(), e);
		}		
	}

	@Override
	public boolean isRunning(){
		return running;
	}
	
	@Override
	public void terminate() {
		running = false;
		synchronized (monitor) {
			notifyed = true;
			monitor.notify();
		}
	}

//	public void doNotify(){
//		doNotify(null);
//	}
	
	private void doNotify(IScanningTaskState jobProperties){
		synchronized (monitor) {
			if(jobProperties!=null){
				scanningJobs.put(jobProperties.getName().toLowerCase(), jobProperties);
			}
			notifyed = true;
			monitor.notify();
		}
	}

	@Override
	public void onStateChanged(IScanningTaskState jobProperties) {
		doNotify(jobProperties);
	}
	
}
