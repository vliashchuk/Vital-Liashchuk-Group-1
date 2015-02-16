package com.epam.jmp.tasks.ep.domain.schedule;

import java.util.Date;

import com.epam.jmp.tasks.ep.domain.CinemaHall;
import com.epam.jmp.tasks.ep.domain.Film;

public interface IShow {
	
	Date getStartTime();

	Date getEndTime();

	CinemaHall getHall();

	Film getFilm();
	
	void setFilm(Film film);

}
