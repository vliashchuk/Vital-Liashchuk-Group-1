package com.epam.jmp.tasks.ddm.domain.schedule;

import java.util.Date;

public interface ITimeFrame {

	Date getStartTime();
	
	Date getEndTime();
	
	IShow[] getShows();
	
}
