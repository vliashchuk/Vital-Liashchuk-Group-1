package com.epam.jmp.tasks.j2ee.hello.ejb.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GreetingEntity implements Greeting{
	
	public GreetingEntity(){}
	
	public GreetingEntity(Greeting base){
		this.id = base.getId();
		this.text = base.getText();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
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
