package com.epam.jmp.tasks.multithreading.folderstatistics.progress;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IProgressPuller;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.IProgressWriter;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.IScanningProgerss;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.ProgressWrittingError;
import com.epam.jmp.tasks.multithreading.folderstatistics.lock.FairLock;
import com.epam.jmp.tasks.multithreading.folderstatistics.task.WaitNotify;

public class ProgressContainer implements IProgressWriter, IProgressPuller {

	private static final Logger LOGGER = Logger.getLogger(ProgressContainer.class);
	
	private FairLock lock = new FairLock();
	private WaitNotify waitNotify = new WaitNotify();
	
	private List<IScanningProgerss > scanningProgerssList = new ArrayList<>();

	@Override
	public void writeProgress(IScanningProgerss progerss) throws ProgressWrittingError {
		try {
			doWriteProgress(progerss);
		} catch (InterruptedException e) {
			LOGGER.error("Error while writing progerss!", e);
			throw new ProgressWrittingError(e);
		}
	}
	
	public void doWriteProgress(IScanningProgerss progerss)
			throws InterruptedException {
		lock.lock();
		scanningProgerssList.add(progerss);
		if(scanningProgerssList.size()==1){
			waitNotify.doNotify();
		}
		lock.unlock();
	}

	@Override
	public IScanningProgerss pollProgress() throws ProgressWrittingError {
		try {

			return doPollProgress();
			
		} catch (InterruptedException e) {
			LOGGER.error("Error while polling progerss!", e);
			throw new ProgressWrittingError(e);
		}
	}
	
	public IScanningProgerss doPollProgress() throws InterruptedException {
		IScanningProgerss ret = null;
		lock.lock();
		if (scanningProgerssList.size() > 0) {
			ret = scanningProgerssList.remove(0);
		}
		lock.unlock();

		if (ret == null) {
			waitNotify.doWait();
		}

		return ret;

	}
	
}
