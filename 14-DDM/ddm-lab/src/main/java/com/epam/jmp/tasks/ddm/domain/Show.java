package com.epam.jmp.tasks.ddm.domain;

import java.util.Date;


public class Show extends AbstractEntity {
	
	private Date startTime;
	private Date endTime;
	
	private CinemaHall hall;
	private Film film;
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public CinemaHall getHall() {
		return hall;
	}
	public void setHall(CinemaHall hall) {
		this.hall = hall;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}

	
	
}
