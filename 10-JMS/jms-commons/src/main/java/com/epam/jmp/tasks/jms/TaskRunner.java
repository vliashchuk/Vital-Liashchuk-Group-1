package com.epam.jmp.tasks.jms;

import java.util.ArrayList;
import java.util.List;

public class TaskRunner implements Task{
	
	public TaskRunner(TaskFactory taskFactory){
		this.taskFactory = taskFactory;
	}
	private TaskFactory taskFactory;
	private int threadsCount = 1;
	private int checkInterval = 1000;
	private boolean isRunning = false;
	
	public int getThreadsCount() {
		return threadsCount;
	}

	public void setThreadsCount(int threadsCount) {
		if(threadsCount>0){
			this.threadsCount = threadsCount;
		}
	}

	public int getCheckInterval() {
		return checkInterval;
	}

	public void setCheckInterval(int checkInterval) {
		if(checkInterval > 0){
			this.checkInterval = checkInterval;
		}
	}

	public boolean isRunning() {
		return isRunning;
	}
	
	public void stop() {
		isRunning = false;
	}

	@Override
	public void run() {
		isRunning = true;
		
		List<Task> tasks = new ArrayList<>();
		
		while(isRunning()){
			
			List<Task> runningTasks = new ArrayList<>();
			for(Task task:tasks){
				if(task.isRunning()){
					runningTasks.add(task);
				}
			}
			
			if(runningTasks.size()<threadsCount){
				int toCreate = threadsCount - runningTasks.size();
				
				for(int i=0; i<toCreate; i++){
					
					Task task = taskFactory.createTask();
					runningTasks.add(task);
					new Thread(task, task.getName()).start();
				}
				
				tasks = runningTasks;
			}
			try {
				Thread.sleep(checkInterval);
			} catch (InterruptedException e) {
				stop();
			}
		}
		
		for(Task task:tasks){
			task.stop();
		}
		
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
}
