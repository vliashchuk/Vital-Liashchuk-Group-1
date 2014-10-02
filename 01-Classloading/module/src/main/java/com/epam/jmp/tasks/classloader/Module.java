package com.epam.jmp.tasks.classloader;

public class Module implements IModule{

	public static final String MODULE_NAME = "module2";
	
	@Override
	public String getName() {
		return MODULE_NAME;
	}

	@Override
	public void execute() {
		System.out.println(Module.class.getName() + ".execute(): Here is: " + MODULE_NAME);
		
	}

}
