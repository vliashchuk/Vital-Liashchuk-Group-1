package com.epam.jmp.tasks.ep.repository;

public class AbstractEntity implements Entity{
	private Long id;
	
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
