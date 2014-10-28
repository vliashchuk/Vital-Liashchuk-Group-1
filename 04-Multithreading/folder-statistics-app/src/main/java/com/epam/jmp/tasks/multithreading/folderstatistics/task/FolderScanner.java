package com.epam.jmp.tasks.multithreading.folderstatistics.task;

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
		return folderStatistics;
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
		
//		public FolderStatistics(FolderStatistics src){
//			this.folderPath = getFolderPath();
//			this.fileCount = getFileCount();
//			this.folderCount = getFolderCount();
//			this.size = getSize();
//		}
		
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
