package com.epam.jmp.tasks.multithreading.folderstatistics.task.view;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.multithreading.folderstatistics.task.IJobProperties;

public class ScanningTaskListView {
	private static final Logger LOGGER = Logger.getLogger(ScanningTaskListView.class);
	
	IJobProperties[] scanningJobs;
	
	public void setScanningJobs(IJobProperties[] scanningJobs){
		this.scanningJobs = scanningJobs;
	}
	
	public void draw(){
		LOGGER.info("SCANNING JOBS:");
		for(IJobProperties scanningJob:scanningJobs){
			StringBuffer string = new StringBuffer();
			string.append("Path: ");
			string.append(scanningJob.getName());
			string.append("; Status: ");
			string.append(scanningJob.isActive() == true ? "Actinve" : "Inactive");
			
			LOGGER.info(string.toString());
		}

	}
}
