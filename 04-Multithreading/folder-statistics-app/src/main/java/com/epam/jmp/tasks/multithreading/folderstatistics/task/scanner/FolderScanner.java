package com.epam.jmp.tasks.multithreading.folderstatistics.task.scanner;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IFolderStatistics;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.IFolderStatisticsProvider;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.ITerminatable;

public class FolderScanner implements ITerminatable, IFolderStatisticsProvider {

	private static final Logger LOGGER = Logger.getLogger(FolderScanner.class);

	private volatile boolean running = true;
	
	private Path folder;
	private FolderStatistics folderStatistics;
	
	public FolderScanner(Path folder) {

		this.folder = folder;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	@Override
	public void terminate(){
	running = false;
	}
	
	@Override
	public FolderStatistics getFolderStatistics(){
		return folderStatistics != null ? new FolderStatistics(folderStatistics) : null;
	}
	
	public void scan() throws IOException {
		
		folderStatistics = new FolderStatistics(folder);
		
		try {
			Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(Path dir,
						BasicFileAttributes attrs) throws IOException  {
					if(!running){
						return FileVisitResult.TERMINATE;
					}
						
					folderStatistics.incrementFolderCount();

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file,
						BasicFileAttributes attrs) throws IOException {
					if(!running){
						return FileVisitResult.TERMINATE;
					}
					
					folderStatistics.incrementFileCount();
					folderStatistics.incrementFileSize(attrs.size());

					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					return FileVisitResult.CONTINUE;
				}

			});
			
			running= false;
			
		} catch (IOException e) {
			running= false;
			LOGGER.error("Error while sacnning folder! (" + folder +")", e);
			throw e;
		}
	}
		
	private class FolderStatistics implements IFolderStatistics{
		
		private Path folderPath;
		private volatile int fileCount = 0;
		private volatile int folderCount = 0;
		private volatile long size = 0;

		public FolderStatistics(Path folderPath){
			this.folderPath = folderPath;
		}
		
		public FolderStatistics(FolderStatistics src){
			this.folderPath = src.getFolderPath();
			this.fileCount = src.getFileCount();
			this.folderCount = src.getFolderCount();
			this.size = src.getSize();
		}
		
		@Override
		public Path getFolderPath() {
			return folderPath;
		}
		
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
	
}
