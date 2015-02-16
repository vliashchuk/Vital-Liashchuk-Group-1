package com.epam.jmp.tasks.ep.domain.schedule;

import java.util.Date;

public interface IDay {

	Date getDate();
	ITimeFrame[] getTimeFrames();
}
