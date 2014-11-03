package com.epam.jmp.tasks.multithreading.folderstatistics;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IFolderScannerApp;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.IScanningTaskListener;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.ITerminatable;
import com.epam.jmp.tasks.multithreading.folderstatistics.task.ListViewDrawingTask;
import com.epam.jmp.tasks.multithreading.folderstatistics.task.ScanningTask;
import com.epam.jmp.tasks.multithreading.folderstatistics.view.ScanningTaskListView;

public class FolderScannerApp implements IFolderScannerApp{

	private static final String APPLICATION_NAME = "Folder Scanning App";
	
	private static final int DEFAULT_SCANNING_POOL_SIZE = 2;
	
	private static final Logger LOGGER = Logger.getLogger(FolderScannerApp.class);
	
	private boolean exited = false;
	
	private Thread listViewDrawingThread;
	private ListViewDrawingTask listViewDrawingTask;
	private Map<String, ITerminatable> scanningTasks = new HashMap<>();
	private ExecutorService executorService;
	
	public FolderScannerApp(Integer scanningPoolSize){
		//Create and start Drawing Job.
		listViewDrawingTask = new ListViewDrawingTask(new ScanningTaskListView());
		listViewDrawingThread = new Thread(listViewDrawingTask);
		listViewDrawingThread.start();
		
		//Create scanning thread pool.
		if(scanningPoolSize != null){
			executorService = Executors.newFixedThreadPool(scanningPoolSize);
		} else {
			executorService = Executors.newFixedThreadPool(DEFAULT_SCANNING_POOL_SIZE);
		}
		
	}
	
	@Override
	public void scan(String folderPath) {
		
		if(exited){
			LOGGER.error("Can't scan because scanner was exited!");
			return;
		}
		
		Path path = Paths.get(folderPath);
		String key = path.toString().toLowerCase();
		
		if(scanningTasks.containsKey(key)){
			if(scanningTasks.get(key).isRunning()){
				LOGGER.error("Can't start scaning folder because it is in progress! ("
							  + path.toString() + ")");
				return;
			}
		}
		
		ScanningTask scanningTask = new ScanningTask(path, Arrays.asList((IScanningTaskListener)listViewDrawingTask));
		
		scanningTasks.put(key,scanningTask);
		listViewDrawingTask.onStateChanged(scanningTask.getTaskState());
		
		executorService.execute(scanningTask);

		
	}

	@Override
	public void stopScanning(String folderPath){
		
		if(exited){
			LOGGER.error("Can't stop scaning because scanner was exited!");
			return;
		}
		
		String key = Paths.get(folderPath).toString().toLowerCase();
		if(scanningTasks.containsKey(key)){
			scanningTasks.get(key).terminate();
		}

	}

	@Override
	public void exit() throws InterruptedException {
		if(!exited){
			
			exited = true;

			if(listViewDrawingThread.isAlive()){
				listViewDrawingTask.terminate();
				listViewDrawingThread.join();

			}
			for(ITerminatable scanningTask:scanningTasks.values()){
				scanningTask.terminate();
			}
			executorService.shutdown();
		}
	}

	@Override
	public String getName() {
		return APPLICATION_NAME;
	}

}
