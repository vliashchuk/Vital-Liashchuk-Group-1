package com.epam.jmp.tasks.classloader;

import org.apache.log4j.Logger;

public class AnotherModule implements IModule{
	public static final Logger LOGGER =Logger.getLogger(AnotherModule.class);
	
	@Override
	public void execute() {
		LOGGER.info(this.getClass().getName() + ".execute()");
	}

}
