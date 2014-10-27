package com.epam.jmp.tasks.multithreading.folderstatistics.task;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IScanningProgerss;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.IFolderStatistics;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.IProgressWriter;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.ITerminatable;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.ProgressWrittingError;

public class ScanningTask implements Runnable, ITerminatable{

	private static final Logger LOGGER = Logger.getLogger(ScanningTask.class);
	
	private static final int PROGRESS_BAR_SIZE = 10;
	private final StringBuilder progressBar = new StringBuilder();
	
	private volatile boolean running = true;
	
	Path folder;
	IProgressWriter progressWriter;
	long fileScanningDelay;
	
	public ScanningTask(Path folder, 
						IProgressWriter progressWriter,
			 			long fileScanningDelay){
		this.folder = folder;
		this.progressWriter = progressWriter;
		this.fileScanningDelay = fileScanningDelay;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	@Override
	public void terminate(){
		running = false;
	}
	
	@Override
	public void run() {
		try {
			final FolderStatistics folderStatistics = new FolderStatistics();
			Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(Path dir,
						BasicFileAttributes attrs) throws IOException {
					if(!isRunning()){
						return FileVisitResult.TERMINATE;
					}
						
					folderStatistics.incrementFolderCount();
					writeProgress();
					try {
						Thread.sleep(fileScanningDelay);
					} catch (InterruptedException e) {
					}
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file,
						BasicFileAttributes attrs) throws IOException {
					if(!isRunning()){
						return FileVisitResult.TERMINATE;
					}
					
					folderStatistics.incrementFileCount();
					folderStatistics.incrementFileSize(attrs.size());
					writeProgress();
					try {
						Thread.sleep(fileScanningDelay);
					} catch (InterruptedException e) {
					}
					return FileVisitResult.CONTINUE;
				}

			});
			
			if(!isRunning()){
				return;
			}
			
			progressWriter.writeProgress(new FolderProgerss(folder, folderStatistics));
			terminate();
			
		} catch (IOException | ProgressWrittingError e) {
			LOGGER.error("Error while sacnning folder! (" + folder +")", e);
		}
	}
	
	private void writeProgress(){
		if(progressBar.length() < PROGRESS_BAR_SIZE){
			progressBar.append(".");
		} else {
			progressBar.setLength(0);
		}

		try {
			progressWriter.writeProgress(new FolderProgerss(folder, progressBar.toString()));
		} catch (ProgressWrittingError e) {
			LOGGER.error("Error while writting progress of scanning folder! (" + folder +")", e);
		}
	}
	
	private class FolderStatistics implements IFolderStatistics{
		
		private int fileCount = 0;
		private int folderCount = 0;
		private long size = 0;

		@Override
		public Integer getFileCount() {
			return fileCount;
		}

		@Override
		public Integer getFolderCount() {
			return folderCount;
		}

		@Override
		public Long getSize() {
			return size;
		}
		
		public void incrementFileCount(){
			fileCount++;
		}
		
		public void incrementFolderCount(){
			folderCount++;
		}
		
		public void incrementFileSize(long value){
			size = size + value;
		}
		
		}
	
	private class FolderProgerss implements IScanningProgerss{

		private Path folderPath;
		
		private String progress;
		
		private boolean complete;
		
		private IFolderStatistics statistics;

		public FolderProgerss(Path folderPath, String progress){
			this.folderPath = folderPath;
			this.progress = progress;
			this.complete = false;
		}
		
		public FolderProgerss(Path folderPath, IFolderStatistics statistics){
			this.folderPath = folderPath;
			this.statistics = statistics;
			this.complete = true;
		}
		
		@Override
		public Path getFolderPath() {
			return folderPath;
		}

		@Override
		public String getProgress() {
			return progress.toString();
		}

		@Override
		public boolean isComplete() {
			return complete;
		}

		@Override
		public IFolderStatistics getStatistics() {
			return statistics;
		}
	}

}
