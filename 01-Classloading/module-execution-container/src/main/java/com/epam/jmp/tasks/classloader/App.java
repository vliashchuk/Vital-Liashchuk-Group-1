package com.epam.jmp.tasks.classloader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
	public static final Logger LOGGER =Logger.getLogger(App.class);
	
	public static final String MODULE_FOLDER_PATH = "modules";
		
	private Map<String, Collection<IModule>> modules = new HashMap<>();
	
	String modulesFolderPath;
	
	public App(){
		this.modulesFolderPath = MODULE_FOLDER_PATH;
	}
	
	public App(String modulesFolderPath){
		if(modulesFolderPath!=null){
			this.modulesFolderPath = modulesFolderPath;
		} else {
			this.modulesFolderPath = MODULE_FOLDER_PATH;
		}
	}
	
	public void deploy(String jarName, String moduleName){
		try {
			JarClassJoader classLoader = new JarClassJoader(modulesFolderPath, jarName);
			Utils utills = new Utils();
			Collection<IModule> m = new ArrayList<IModule>();
			for(Class<IModule> moduleClazz:utills.getJarClassesOfInterface(classLoader, IModule.class))
			{
				m.add((IModule)moduleClazz.newInstance());
			}
			
			undeploy(moduleName, false);
			modules.put(moduleName, m);
			LOGGER.info("Module '" + moduleName + "' successfully deployed.");
			
		} catch (IOException e) {
			LOGGER.error("Error: Error while accessing jar!", e);
		} catch (ClassNotFoundException e) {
			LOGGER.error("Error: Error while loading class!", e);
		} catch (Exception e) {
			LOGGER.error("Error: Unexpected error!", e);
		}
	}
	public void undeploy(String moduleName){
		undeploy(moduleName, true);
	}
	
	public void undeploy(String moduleName, boolean check){
		if(moduleName != null && modules.containsKey(moduleName)){
			modules.remove(moduleName);
			LOGGER.info("Module '" + moduleName + "' undeployed.");
		} else {
			if(check){
				LOGGER.error("Error: Module '" + moduleName + "' was not deployed.");
			}
		}
	}
	
	public void execute(String moduleName){
		if(modules.get(moduleName) != null){
			for(IModule m:modules.get(moduleName)){
				m.execute();
			}
		} else {
			LOGGER.error("Error: Module '" + moduleName + "' was not deployed!");
		}
	}
	
	public void list(){
		LOGGER.info("Module list:");
		for(String name:modules.keySet()){
			LOGGER.info(name);
		}
	}
	
}
