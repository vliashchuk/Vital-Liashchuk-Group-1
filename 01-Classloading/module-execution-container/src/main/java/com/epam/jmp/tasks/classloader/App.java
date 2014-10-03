package com.epam.jmp.tasks.classloader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
	public static final String MODULE_FOLDER_PATH = "modules";
	public static final String MODULE_CLASS_NAME = "com.epam.jmp.tasks.classloader.Module";
		
	private Map<String, IModule> modules = new HashMap<>();
	
	
	public void deploy(String jarName){
		try {
			ClassLoader classLoader = new JarClassJoader(MODULE_FOLDER_PATH, jarName);
			Class<?> moduleClazz = classLoader.loadClass(MODULE_CLASS_NAME);

			IModule module = (IModule)moduleClazz.newInstance();
	
			undeploy(module.getName());
			modules.put(module.getName(), module);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void undeploy(String moduleName){
		if(moduleName != null && modules.containsKey(moduleName)){
			modules.remove(moduleName);
		}
	}
	
	public void execute(String moduleName){
		if(modules.get(moduleName) != null){
			 modules.get(moduleName).execute(); 
		} else {
			System.err.println(moduleName);
		}
	}
	
	public void list(){
		for(String name:modules.keySet()){
			System.out.println(name);
		}
	}
	
}
