package com.epam.jmp.tasks.ep.dao;

public interface GenericDao <T> {

	T insert(T item);
	
	void update(T item);
	
	void delete(T item);
	
}
