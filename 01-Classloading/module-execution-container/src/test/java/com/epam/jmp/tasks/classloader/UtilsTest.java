package com.epam.jmp.tasks.classloader;

import java.io.IOException;
import java.util.Collection;

public class UtilsTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException{
		
		Utils utills = new Utils();
		JarClassJoader loader = new JarClassJoader("module", "module-1.0-SNAPSHOT.jar");
		Collection<String> classNames = utills.getJarClassNames(loader.getJarFile());
		System.out.println(classNames);
		Collection<Class<?>> classes = utills.getJarClasses(loader);
		System.out.println(classes);
		Collection<Class<IModule>> moduleClasses = utills.getJarClassesOfInterface(loader, IModule.class);
		System.out.println(moduleClasses);

	}
	
}
