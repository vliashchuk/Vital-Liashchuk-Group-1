package com.epam.jmp.tasks.ddm.domain.schedule;

import java.util.Date;

import com.epam.jmp.tasks.ddm.domain.CinemaHall;
import com.epam.jmp.tasks.ddm.domain.Film;


public class Show implements IShow{
	
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
