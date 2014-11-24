package com.epam.jmp.tasks.j2ee.bookslibrary.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
@Remote(BookManager.class)
@DeclareRoles("bean")
public class BookManagerBean implements BookManager, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 393498984449481604L;
	List<Book> books = new ArrayList<>();
	
	@Override
	@RolesAllowed("bean")
	public void addBook(Book book) {
		books.add(book);
	}

	@Override
	@RolesAllowed("bean")
	public List<Book> listBooks() {
		return books;
	}

	@Override
	@RolesAllowed("bean")
	public void removeBook(int id) {
		Book toRemove = null;
		for(Book book:books){
			if(book.getId() == id){
				toRemove = book;
				break;
			}
		}
		if(toRemove!=null){
			books.remove(toRemove);
		}
	}

	@Remove
	@Override
	@RolesAllowed("bean")
	public void destruct() {
		System.out.println("Removing BookManagerBean...");
	}

}
