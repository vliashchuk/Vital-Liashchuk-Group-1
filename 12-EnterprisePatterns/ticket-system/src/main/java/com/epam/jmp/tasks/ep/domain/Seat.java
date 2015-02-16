package com.epam.jmp.tasks.ep.domain;

public class Seat {
	private Integer rawNumber;
	private Integer palceNumber;

	public Seat(Integer rawNumber, Integer palceNumber) {
		super();
		this.rawNumber = rawNumber;
		this.palceNumber = palceNumber;
	}

	public Integer getRawNumber() {
		return rawNumber;
	}
	public Integer getPalceNumber() {
		return palceNumber;
	}

	
}
