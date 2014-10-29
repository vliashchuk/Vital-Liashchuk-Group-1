package com.epam.jmp.tasks.multithreading.folderstatistics.task.scanner;

public class Test {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		FolderScannerApp app = new FolderScannerApp();
		
		app.scan("D:/TMP/1");
		app.showDetails("D:/TMP/1");
		Thread.sleep(5000);
		app.scan("D:/TMP/2");
		app.showDetails("D:/TMP/2");
		Thread.sleep(5000);
		app.scan("D:/TMP/3");
		app.showDetails("D:/TMP/3");
		Thread.sleep(5000);
		app.scan("D:/TMP/3");
		
		System.out.println("END!");
		
//		Thread.sleep(500);
//		app.stopScanning("D:/TMP/1");
//		Thread.sleep(500);
//		app.stopScanning("D:/TMP/2");
//		Thread.sleep(500);
//		app.exit();
		
	}

}
