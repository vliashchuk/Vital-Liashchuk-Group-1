package com.epam.jmp.tasks.multithreading.folderstatistics.task;

import com.epam.jmp.tasks.multithreading.folderstatistics.task.view.ScanningTaskListView;


public class ListViewDrawingTask extends AbstractDrawingTask<IJobProperties[]>{

	IJobProperties[] scanningJobs;

	private ScanningTaskListView view;
	
	public ListViewDrawingTask(ScanningTaskListView view){
		super();
		this.view = view;
	}
	
	public ListViewDrawingTask(ScanningTaskListView view, int outputTimeout){
		super(outputTimeout);
		this.view = view;
	}
	
	public void setScanningJobs(IJobProperties[] scanningJobs){
		this.scanningJobs = scanningJobs;
	}

	@Override
	protected IJobProperties[] draw(IJobProperties[] lastDrawnObject) {

		if(hasScanningJobsChanged(lastDrawnObject, scanningJobs)){
			view.setScanningJobs(scanningJobs);
			view.draw();	
		}
		return createCopy(scanningJobs);
		
		
	}

	private boolean hasScanningJobsChanged(IJobProperties[] oldVal, IJobProperties[] newVal){
		if(newVal == null
				||
		   oldVal == newVal){
			return false;
		}
		
		if ((oldVal == null && newVal != null)
				||
			(oldVal.length != newVal.length)) {
			return true;
		}
		

		for(int i=0; i<oldVal.length; i++){
			if(oldVal[i].getName() != newVal[i].getName()
				||
				oldVal[i].isActive() != newVal[i].isActive()){
				return true;
			}
		}
		return false;

	}
	
	private IJobProperties[] createCopy(IJobProperties[] src){
		
		IJobProperties[] ret = new IJobProperties[src.length];
		
		for(int i= 0 ; i<ret.length; i++){
			ret[i] = new JobProperties(src[i]);
		}
		
		return ret;
	}

	private class JobProperties implements IJobProperties{

		private String name;
		private boolean active;
		
		public JobProperties(IJobProperties src){
			this.name = src.getName();
			this.active = src.isActive();
		}
		
		@Override
		public String getName() {
			return name;
		}

		@Override
		public boolean isActive() {
			return active;
		}
		
	}
	
}
