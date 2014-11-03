package com.epam.jmp.tasks.multithreading.folderstatistics.core;

public interface IScanningTaskListener {

	void onStateChanged(IScanningTaskState jobProperties);
	
}
