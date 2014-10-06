package com.epam.jmp.tasks.classloader;

import org.apache.log4j.Logger;

public class ModulePart {
	public static final Logger LOGGER =Logger.getLogger(ModulePart.class);
	
	public void execute(){
		LOGGER.info(this.getClass().getName() + ".execute()");
	}
}
