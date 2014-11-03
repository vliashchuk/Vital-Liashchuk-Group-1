package com.epam.jmp.tasks.multithreading.folderstatistics.core;

import com.epam.jmp.tasks.multithreading.folderstatistics.scanner.TaskStatus;

public interface IScanningTaskState {

	String getName();
	
	TaskStatus getStatus();
	
	IFolderStatistics getFolderStatistics();
	
}
