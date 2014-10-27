package com.epam.jmp.tasks.multithreading.folderstatistics.scanner;

import java.nio.file.Paths;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IFolderStatisticsScanner;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.IProgressWriter;
import com.epam.jmp.tasks.multithreading.folderstatistics.task.ScanningTask;

public class SerialFolderStatisticsScanner implements IFolderStatisticsScanner {

	IProgressWriter outputWriter;
	
	public SerialFolderStatisticsScanner(IProgressWriter outputWriter){
		this.outputWriter = outputWriter;
	}	
	
	@Override
	public void scan(String folderPath) {
		ScanningTask scanningTask = new ScanningTask(
				Paths.get(folderPath),
				outputWriter,
				1000);
		scanningTask.run();
	}

	/**
	 * We don't need this method.
	 * @param folderPath folder to stop scanning
	 */
	@Override
	public void stopScanning(String folderPath) {
	}

	@Override
	public void exit() {
	}

}
