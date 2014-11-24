package com.epam.jmp.tasks.j2ee.bookslibrary.ejb;

import java.io.Serializable;

public class SimpleBook implements Book, Serializable{

	/**
	 * Serial uid.
	 */
	private static final long serialVersionUID = 3973624253842010610L;

	public SimpleBook(int id){
		this.id = id;
	}
	
	private int id;
	
	private String name;
	
	private String author;
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public int getId() {
		return id;
	}
	
	public String toString(){
		return "Book '" + name +
			   "' of author " + author +
			   " (id=" + id + ")";
	}
	
}
