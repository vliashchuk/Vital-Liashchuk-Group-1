package com.epam.jmp.tasks.j2ee.bookslibrary.ejb;

import java.util.List;

public interface BookManager {

	void addBook(Book book);
	List<Book> listBooks();
	void removeBook(int id);
	void destruct();
	
}
