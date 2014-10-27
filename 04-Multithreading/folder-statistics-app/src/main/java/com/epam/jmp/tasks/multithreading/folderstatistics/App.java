package com.epam.jmp.tasks.multithreading.folderstatistics;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IFolderStatisticsScanner;
import com.epam.jmp.tasks.multithreading.folderstatistics.progress.LogProgressWriter;
import com.epam.jmp.tasks.multithreading.folderstatistics.scanner.ConcurrentFolderStatisticsScanner;

public class App {

	public static void main(String[] args) throws InterruptedException {

		IFolderStatisticsScanner scanner = new ConcurrentFolderStatisticsScanner(
				new LogProgressWriter());
		
		scanner.scan("D:/TMP/1");
		scanner.scan("D:/TMP/2");
		scanner.scan("D:/TMP/3");
		scanner.scan("D:/TMP/3");
		
		Thread.sleep(500);
		scanner.stopScanning("D:/TMP/1");
		Thread.sleep(500);
		scanner.stopScanning("D:/TMP/2");
		Thread.sleep(500);
		scanner.exit();
		
	}

}
