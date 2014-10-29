package com.epam.jmp.tasks.multithreading.folderstatistics.task.utils;

public class WaitNotify {

	  MonitorObject myMonitorObject = new MonitorObject();
	  boolean wasSignalled = false;

	  public void doWait() throws InterruptedException{
	    synchronized(myMonitorObject){
	      while(!wasSignalled){

	          myMonitorObject.wait();

	      }
	      //clear signal and continue running.
	      wasSignalled = false;
	    }
	  }

	  public void doNotify(){
	    synchronized(myMonitorObject){
	      wasSignalled = true;
	      myMonitorObject.notify();
	    }
	  }
	  
	  private class MonitorObject{
	  }
	}