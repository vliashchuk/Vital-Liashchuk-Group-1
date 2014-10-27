package com.epam.jmp.tasks.multithreading.folderstatistics.lock;

public class WaitNotify {

	  MonitorObject myMonitorObject = new MonitorObject();
	  boolean wasSignalled = false;

	  public void doWait(){
	    synchronized(myMonitorObject){
	      if(!wasSignalled){
	        try{
	          myMonitorObject.wait(10);
	         } catch(InterruptedException e){}
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