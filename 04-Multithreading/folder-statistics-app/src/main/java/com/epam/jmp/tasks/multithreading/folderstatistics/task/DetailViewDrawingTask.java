package com.epam.jmp.tasks.multithreading.folderstatistics.task;

import com.epam.jmp.tasks.multithreading.folderstatistics.core.IFolderStatistics;
import com.epam.jmp.tasks.multithreading.folderstatistics.core.IFolderStatisticsProvider;
import com.epam.jmp.tasks.multithreading.folderstatistics.task.view.ScanningTaskDetailView;

public class DetailViewDrawingTask extends AbstractDrawingTask<IFolderStatistics>{
	
	private IFolderStatisticsProvider folderStatisticsProvider;
	private ScanningTaskDetailView view;

	
	public DetailViewDrawingTask(ScanningTaskDetailView view){
		super();
		this.view = view;
	}
	
	public DetailViewDrawingTask(ScanningTaskDetailView view, int outputTimeout){
		super(outputTimeout);
		this.view = view;
	}

	public void setFolderStatisticsProvider(IFolderStatisticsProvider folderStatisticsProvider){
		this.folderStatisticsProvider = folderStatisticsProvider;
	}
	
	@Override
	protected IFolderStatistics draw(IFolderStatistics lastDrawnObject) {
		IFolderStatistics currentStatistics = null;
		
		if(folderStatisticsProvider != null){
			currentStatistics = folderStatisticsProvider.getFolderStatistics();
			if(hasStatisticsChanged(lastDrawnObject, currentStatistics)){
				view.setFolderStatistics(currentStatistics);
				view.draw();
			}
		}

		return currentStatistics;
	}
	
	private boolean hasStatisticsChanged(IFolderStatistics oldVal, IFolderStatistics newVal){
		
		if(newVal == null
				||
		   oldVal == newVal){
			return false;
		}

		if((oldVal == null && newVal != null)
		    ||
		   !oldVal.getFolderPath().equals(newVal.getFolderPath())
			||
		   !oldVal.getFolderCount().equals(newVal.getFolderCount())
			||
		   !oldVal.getFileCount().equals(newVal.getFileCount())
			||
		   !oldVal.getSize().equals(newVal.getSize())){
			return true;
		} else {
			return false;
		}
	}



}
