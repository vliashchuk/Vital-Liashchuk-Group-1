package com.epam.jmp.tasks.multithreading.folderstatistics.task.scanner;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.multithreading.folderstatistics.task.DetailViewDrawingTask;
import com.epam.jmp.tasks.multithreading.folderstatistics.task.ListViewDrawingTask;
import com.epam.jmp.tasks.multithreading.folderstatistics.task.ScanningJob;
import com.epam.jmp.tasks.multithreading.folderstatistics.task.ScanningTask;
import com.epam.jmp.tasks.multithreading.folderstatistics.task.view.ScanningTaskDetailView;
import com.epam.jmp.tasks.multithreading.folderstatistics.task.view.ScanningTaskListView;

public class FolderScannerApp implements IFolderScannerApp{

	private static final Logger LOGGER = Logger.getLogger(FolderScannerApp.class);
	
	private boolean exited = false;
	
	Thread detailViewDrawingThread;
	DetailViewDrawingTask detailViewDrawingTask;
	Thread listViewDrawingThread;
	ListViewDrawingTask listViewDrawingTask;
	Map<String,ScanningJob> scanningJobs = new HashMap<>();
	
	public FolderScannerApp(){
		initDrawingJobs();
	}
	
	/**
	 *  Creates and starts Drawing Jobs.
	 */
	private void initDrawingJobs() {
		detailViewDrawingTask =
				new DetailViewDrawingTask(new ScanningTaskDetailView());
		detailViewDrawingTask.pause();
		detailViewDrawingThread = new Thread(detailViewDrawingTask);
		detailViewDrawingThread.start();
		
		listViewDrawingTask = new ListViewDrawingTask(new ScanningTaskListView());
		listViewDrawingTask.pause();
		listViewDrawingThread = new Thread(listViewDrawingTask);
		listViewDrawingThread.start();
	}
	
	@Override
	public void scan(String folderPath) {
		
		if(exited){
			LOGGER.error("Can't scan because scanner was exited!");
			return;
		}
		
		listViewDrawingTask.pause();
		detailViewDrawingTask.pause();
		
		Path path = Paths.get(folderPath);
		String key = path.toString().toLowerCase();
		
		if(scanningJobs.containsKey(key)){
			if(scanningJobs.get(key).isActive()){
				LOGGER.error("Can't start scaning folder because it is in progress! ("
							  + path.toString() + ")");
				
				listViewDrawingTask.resume();
				return;
			}
		}
		
		ScanningTask scanningTask = new ScanningTask(
				path);
		Thread scanningThread = new Thread(scanningTask);
		
		scanningJobs.put(key,
						new ScanningJob(path.toString(),scanningThread, scanningTask));
		scanningThread.start();
		
		listViewDrawingTask.setScanningJobs(scanningJobs.values().toArray(new ScanningJob[scanningJobs.size()]));
		listViewDrawingTask.resume();
		
	}



	@Override
	public void stopScanning(String folderPath) throws InterruptedException {
		
		if(exited){
			LOGGER.error("Can't stop scaning because scanner was exited!");
			return;
		}
		
		listViewDrawingTask.pause();
		detailViewDrawingTask.pause();
		
		String key = Paths.get(folderPath).toString().toLowerCase();
		if(scanningJobs.containsKey(key)){
			scanningJobs.get(key).stop();
		}
		listViewDrawingTask.resume();
	}

	@Override
	public void exit() throws InterruptedException {
		exited = true;
		if(detailViewDrawingThread.isAlive()){
			detailViewDrawingTask.terminate();
			detailViewDrawingThread.join();

		}
		if(listViewDrawingThread.isAlive()){
			listViewDrawingTask.terminate();
			listViewDrawingThread.join();

		}
		for(ScanningJob job:scanningJobs.values()){
			job.stop();
		}
		scanningJobs.clear();
		
	}

	@Override
	public void showDetails(String folderPath) {
		listViewDrawingTask.pause();
		String key = Paths.get(folderPath).toString().toLowerCase();
		detailViewDrawingTask.setFolderStatisticsProvider(scanningJobs.get(key).getFolderStatisticsProvider());
		detailViewDrawingTask.resume();
	}

	@Override
	public void showList() {
		listViewDrawingTask.resume();
		detailViewDrawingTask.pause();
	}

}
