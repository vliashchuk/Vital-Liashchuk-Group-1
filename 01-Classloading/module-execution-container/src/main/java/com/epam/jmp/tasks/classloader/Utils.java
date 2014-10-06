package com.epam.jmp.tasks.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class Utils {

	public Collection<String> getJarClassNames(File jarFile) throws IOException{
		
		Collection<String> classNames = new ArrayList<String>();
		
		JarInputStream jar=new JarInputStream(new FileInputStream(jarFile));
		for(JarEntry entry=jar.getNextJarEntry();entry!=null;entry=jar.getNextJarEntry())
		    if(entry.getName().endsWith(".class") && !entry.isDirectory()) {
		        StringBuilder className=new StringBuilder();
		        for(String part : entry.getName().split("/")) {
		            if(className.length() != 0)
		                className.append(".");
		            className.append(part);
		            if(part.endsWith(".class"))
		                className.setLength(className.length()-".class".length());
		        }
		        classNames.add(className.toString());
		    }
		jar.close();
		
		return classNames;
	}
	
	public Collection<Class<?>> getJarClasses(JarClassJoader classLoader) throws IOException, ClassNotFoundException{
		
		Collection<String> classNames = getJarClassNames(classLoader.getJarFile());
		Collection<Class<?>> classes = new ArrayList<Class<?>>();
		
		for(String className:classNames){
			classes.add(classLoader.loadClass(className));
		}
		
		return classes;
	}
	
	@SuppressWarnings("unchecked")
	public <T> Collection<Class<T>> getJarClassesOfInterface(JarClassJoader classLoader, Class<T> interfacee) throws IOException, ClassNotFoundException{
		
		Collection<Class<?>> classes = getJarClasses(classLoader);
		Collection<Class<T>> ret = new ArrayList<Class<T>>();
		
		for(Class<?> clazz:classes){
			Class<?>[] interfaces = clazz.getInterfaces();
			for(Class<?> i:interfaces){
				if(i.equals(interfacee)){
					ret.add((Class<T>) clazz);
					break;
				}
			}
			
		}
		
		return ret;
	}	
	
}
