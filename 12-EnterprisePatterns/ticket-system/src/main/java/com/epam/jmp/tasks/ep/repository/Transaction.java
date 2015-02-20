package com.epam.jmp.tasks.ep.repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Transaction {

	private Thread lockingThread;
	
	private int stateId = 0; // 0-started, 1-commited, 2-rollbacked

	private List<Transactional> transactionParticipants = new CopyOnWriteArrayList<>();
	
	public Transaction(Thread lockingThread){
		this.lockingThread = lockingThread;
	}

	public int getStateId() {
		return stateId;
	}
	
	public boolean isHoldingThread(){
		return lockingThread==Thread.currentThread();
	}
	
	public void commit(){
		if(isHoldingThread()){
			stateId = 1;
			for(Transactional participant:transactionParticipants){
				participant.commit();
			}
		} else {
			throw new IllegalStateException("Commit can  be made only by thread owner of transaction.");
		}	
	}
	
	public void rollback(){
		if(isHoldingThread()){
			stateId = 2;
			for(Transactional participant:transactionParticipants){
				participant.rollback();
			}
		} else {
			throw new IllegalStateException("Rollback can  be made only by thread owner of transaction.");
		}
	}
	
	public void addParticipant(Transactional participant){
		transactionParticipants.add(participant);
	}
}
