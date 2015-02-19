package com.epam.jmp.tasks.ep.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionManager {

	private Map<Thread, Transaction> transactions = new ConcurrentHashMap<>();
	
	public Transaction createTransaction(){
		Thread currentThread = Thread.currentThread();
		Transaction transaction = transactions.get(currentThread);
		if(transaction == null){
			transaction = new Transaction();
			transaction.setLockingThread(currentThread);
		}
		return transaction;
	}
	
}
