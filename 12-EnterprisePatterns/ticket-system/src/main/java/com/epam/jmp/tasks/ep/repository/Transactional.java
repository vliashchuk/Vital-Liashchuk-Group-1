package com.epam.jmp.tasks.ep.repository;

public interface Transactional {
	void commit();
	void rollback();
}
