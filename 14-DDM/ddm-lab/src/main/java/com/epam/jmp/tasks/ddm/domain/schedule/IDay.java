package com.epam.jmp.tasks.ddm.domain.schedule;

import java.util.Date;

public interface IDay {

	Date getDate();
	ITimeFrame[] getTimeFrames();
}
