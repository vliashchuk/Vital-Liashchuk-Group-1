package com.epam.jmp.tasks.ddm.domain;

public class Seat extends AbstractEntity {
	
	private CinemaHall cinemaHall;
	
	private Integer rawNumber;
	private Integer palceNumber;

	public Seat(CinemaHall cinemaHall, Integer rawNumber, Integer palceNumber) {
		super();
		this.cinemaHall = cinemaHall;
		this.rawNumber = rawNumber;
		this.palceNumber = palceNumber;
	}

	public CinemaHall getCinemaHall() {
		return cinemaHall;
	}
	public Integer getRawNumber() {
		return rawNumber;
	}
	public Integer getPalceNumber() {
		return palceNumber;
	}


	
}
