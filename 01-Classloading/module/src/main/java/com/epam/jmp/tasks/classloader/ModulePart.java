package com.epam.jmp.tasks.classloader;

public class ModulePart {
	
	private IModule module;
	
	public ModulePart(IModule module){
		this.module = module;
	}
	
	public void execute(){
		System.out.println(this.getClass().getName() + ".execute(): Here is part of module: " + module.getName());
	}

}
