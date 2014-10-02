package com.epam.jmp.tasks.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarClassJoader extends ClassLoader{

	private final JarFile jar;
	
	public JarClassJoader(String path, String jarName) throws IOException{
		jar = new JarFile(new File(path, jarName));
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		
		JarEntry entry = jar.getJarEntry(name.replace(".", "/")+ ".class");
		
		if(entry == null){
			throw new ClassNotFoundException(name);
		}

		try {
	        byte[] array = new byte[1024];
	        InputStream in;
			in = jar.getInputStream(entry);
	        ByteArrayOutputStream out = new ByteArrayOutputStream(array.length);
	        int length = in.read(array);
	        while (length > 0) {
	            out.write(array, 0, length);
	            length = in.read(array);
	        }
	        Class<?> c = defineClass(name, out.toByteArray(), 0, out.size()); 
	        resolveClass(c);
			return c;
		} catch (IOException e) {
			throw new ClassNotFoundException(name, e);
		}

	}
	
	
}
