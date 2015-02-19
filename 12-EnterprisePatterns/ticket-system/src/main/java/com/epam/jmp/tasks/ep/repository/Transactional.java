package com.epam.jmp.tasks.ep.repository;

public interface Transactional {
	void setTransactionManager(TransactionManager transactionManager);
	void commit();
	void rollback();
}
