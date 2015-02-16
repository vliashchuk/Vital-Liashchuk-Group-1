package com.epam.jmp.tasks.ep.service;

import java.util.Date;

import com.epam.jmp.tasks.ep.domain.CinemaHall;
import com.epam.jmp.tasks.ep.domain.schedule.IDay;
import com.epam.jmp.tasks.ep.domain.schedule.IShow;
import com.epam.jmp.tasks.ep.domain.schedule.ITimeFrame;

public interface ISceduleService {

	IDay createDay(Date date);
	
	ITimeFrame createTimeFrame(Date startTime, Date endTime);
	
	IShow createShow(Date startTime, Date endTime, CinemaHall cinemaHall);
	
	void deleteDay(IDay day);
	
	void deleteTimeFrame(ITimeFrame timeFrame);
	
	void deleteShow(IShow day);
}
