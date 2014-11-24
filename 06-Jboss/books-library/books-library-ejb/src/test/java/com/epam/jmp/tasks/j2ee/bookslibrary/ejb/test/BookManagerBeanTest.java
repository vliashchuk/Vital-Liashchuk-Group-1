package com.epam.jmp.tasks.j2ee.bookslibrary.ejb.test;

import java.util.List;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.jmp.tasks.j2ee.bookslibrary.ejb.Book;
import com.epam.jmp.tasks.j2ee.bookslibrary.ejb.BookManager;
import com.epam.jmp.tasks.j2ee.bookslibrary.ejb.BookManagerBean;

public class BookManagerBeanTest {
	@Test
	public void test(){
		
		BookManager manager = new BookManagerBean();
		Book book = Mockito.mock(Book.class);
		Mockito.when(book.getId()).thenReturn(1);
		manager.addBook(book);
		book = Mockito.mock(Book.class);
		Mockito.when(book.getId()).thenReturn(2);
		manager.addBook(book);
		List<Book> books = manager.listBooks();
		Assert.assertEquals(2, books.size());
		manager.removeBook(book.getId());
		books = manager.listBooks();
		Assert.assertEquals(1, books.size());
		
	}
}
