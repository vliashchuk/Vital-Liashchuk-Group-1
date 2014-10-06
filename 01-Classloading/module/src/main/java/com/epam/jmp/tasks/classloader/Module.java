package com.epam.jmp.tasks.classloader;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Module implements IModule{
	public static final Logger LOGGER =Logger.getLogger(Module.class);
	
	private List<ModulePart> moduleParts = new ArrayList<>();
	
	public Module(){
		moduleParts.add(new ModulePart());
	}

	@Override
	public void execute() {
		LOGGER.info(this.getClass().getName() + ".execute()");
		for (ModulePart modulePart : moduleParts) {
			modulePart.execute();
		}
		
	}

}
