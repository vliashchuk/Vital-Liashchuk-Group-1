package com.epam.jmp.tasks.multithreading.folderstatistics.view;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IScanningTaskState;

public class ScanningTaskListView {
	private static final Logger LOGGER = Logger.getLogger(ScanningTaskListView.class);
	
	IScanningTaskState[] scanningJobs;
	
	public void setScanningJobs(IScanningTaskState[] scanningJobs){
		this.scanningJobs = scanningJobs;
	}
	
	public void draw(){
		LOGGER.info("SCANNING JOBS:");
		if(scanningJobs == null || scanningJobs.length==0){
			LOGGER.info("No jobs.");
			return;
		}
		for(IScanningTaskState scanningJob:scanningJobs){
			StringBuffer string = new StringBuffer();
			string.append(scanningJob.getName());
			string.append("; Status: ");
			string.append(scanningJob.getStatus().toString());
			if(scanningJob.getFolderStatistics() != null){
				string.append("; Size: ");
				string.append(scanningJob.getFolderStatistics().getSize());
				string.append("; Files: ");
				string.append(scanningJob.getFolderStatistics().getFileCount());
				string.append("; Dirs: ");
				string.append(scanningJob.getFolderStatistics().getFolderCount());
			}
			
			LOGGER.info(string.toString());
		}

	}
}
