package com.epam.jmp.tasks.multithreading.folderstatistics.scanner;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IFolderStatistics;

public class FolderScannerTest {

	FolderScanner folderScanner;
	int dirsCount = 0;
	int filesCount = 0;
	long size = 0;
	Path dir1;
	
	@Before
	public void prepere() throws URISyntaxException, IOException{
		
		Path folder =new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).toPath();
		dir1 = Paths.get(folder.toString(), "1");
		if(dir1.toFile().exists()){
			deleteFolder(dir1.toFile());
		}
		Files.createDirectory(dir1);
		dirsCount++;
		Path dir1_2 = Paths.get(dir1.toString(), "2");
		Files.createDirectory(dir1_2);
		dirsCount++;
		Path dir1_3 = Paths.get(dir1.toString(), "3");
		Files.createDirectory(dir1_3);
		dirsCount++;
		Path file1_2_1 = Paths.get(dir1_2.toString(), "1");
		String file1_2_1_content = "file1_2_1_content";
		Files.createFile(file1_2_1);
		Files.write(file1_2_1, file1_2_1_content.getBytes(Charset.forName("utf8")));
		size = size + file1_2_1_content.getBytes(Charset.forName("utf8")).length;
		filesCount++;
		
		Path file1_3_2 = Paths.get(dir1_3.toString(), "2");
		String file1_3_2_content = "file1_3_2_content";
		Files.createFile(file1_3_2);
		Files.write(file1_3_2, file1_3_2_content.getBytes(Charset.forName("utf8")));
		size = size + file1_3_2_content.getBytes(Charset.forName("utf8")).length;
		filesCount++;
		
		folderScanner = new FolderScanner(dir1);
	}
	
	@Test
	public void test() throws IOException{
		folderScanner.scan();
		IFolderStatistics statistics = folderScanner.getFolderStatistics();
		Assert.assertEquals(dirsCount, statistics.getFolderCount().intValue());
		Assert.assertEquals(filesCount, statistics.getFileCount().intValue());
		Assert.assertEquals(size, statistics.getSize().longValue());
	}
	
	@After
	public void clean() throws IOException{
		deleteFolder(dir1.toFile());
	}
	
	private void deleteFolder(File folder) {
	    File[] files = folder.listFiles();
	    if(files!=null) { //some JVMs return null for empty dirs
	        for(File f: files) {
	            if(f.isDirectory()) {
	                deleteFolder(f);
	            } else {
	                f.delete();
	            }
	        }
	    }
	    folder.delete();
	}
	
}
