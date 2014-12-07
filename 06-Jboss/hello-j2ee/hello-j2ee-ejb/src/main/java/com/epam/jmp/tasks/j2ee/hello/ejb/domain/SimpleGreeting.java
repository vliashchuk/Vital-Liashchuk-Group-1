package com.epam.jmp.tasks.j2ee.hello.ejb.domain;

import java.io.Serializable;

public class SimpleGreeting implements Greeting, Serializable{

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 8424222927847568439L;

	private int id;
	private String text;
	
	@Override
	public int getId() {
		return id;
	}
	@Override
	public String getText() {
		return text;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setText(String text) {
		this.text = text;
	}



}
