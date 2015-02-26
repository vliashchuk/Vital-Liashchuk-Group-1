package com.epam.jmp.tasks.ddm.domain;

import com.epam.jmp.tasks.ddm.repository.AbstractEntity;


public class User extends AbstractEntity{
	
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
