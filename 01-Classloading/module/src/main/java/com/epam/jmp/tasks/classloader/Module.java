package com.epam.jmp.tasks.classloader;

import java.util.ArrayList;
import java.util.List;

public class Module implements IModule{

	public static final String MODULE_NAME = "module3";
	
	private List<ModulePart> moduleParts = new ArrayList<>();
	
	public Module(){
		moduleParts.add(new ModulePart(this));
	}
	
	@Override
	public String getName() {
		return MODULE_NAME;
	}

	@Override
	public void execute() {
		System.out.println(this.getClass().getName() + ".execute(): Here is: " + MODULE_NAME);
		for (ModulePart modulePart : moduleParts) {
			modulePart.execute();
		}
		
	}

}
