package com.epam.jmp.tasks.ep.repository;

public class Transaction {

	private Thread lockingThread = null;
	
	int stateId = 0; // 0-started, 1-commited, 2-rollbacked

	public Thread getLockingThread() {
		return lockingThread;
	}

	public void setLockingThread(Thread lockingThread) {
		this.lockingThread = lockingThread;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	
	public boolean isHoldingThread(){
		return lockingThread==Thread.currentThread();
	}
	
	public void waitUntilTransactionEnds(){
		
	}
	
}
