package com.epam.jmp.tasks.ddm.domain.schedule;

import java.util.ArrayList;
import java.util.Date;

public class Day implements IDay {

	private Date date;

	private ArrayList<TimeFrame> timeFrames = new ArrayList<TimeFrame>();


	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public ITimeFrame[] getTimeFrames() {
		return timeFrames.toArray(new TimeFrame[timeFrames.size()]);
	}

	public void addTimeFrame(TimeFrame timeFrame){
		timeFrames.add(timeFrame);
	}

	public void deleteTimeFrame(TimeFrame timeFrame){
		timeFrames.remove(timeFrame);
	}

}
