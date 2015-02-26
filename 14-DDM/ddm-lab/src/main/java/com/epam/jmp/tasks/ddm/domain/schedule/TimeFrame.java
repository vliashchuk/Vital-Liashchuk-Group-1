package com.epam.jmp.tasks.ddm.domain.schedule;

import java.util.ArrayList;
import java.util.Date;

public class TimeFrame implements ITimeFrame {

	private Date startTime;
	private Date endTime;
	
	ArrayList<Show> shows = new ArrayList<Show>(); 

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

	public IShow[] getShows() {
		return shows.toArray(new Show[shows.size()]);
	}
	
}
