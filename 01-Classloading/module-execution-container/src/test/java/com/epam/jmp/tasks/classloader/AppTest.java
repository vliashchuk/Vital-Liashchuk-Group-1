package com.epam.jmp.tasks.classloader;

public class AppTest {

	public static void main(String[] args){
		
		App app = new App();
//		app.deploy("module1-1.0-SNAPSHOT.jar");
//		app.list();
//		app.execute("module1");
//		app.deploy("module2-1.0-SNAPSHOT.jar");
//		app.list();
//		app.execute("module2");
//		app.undeploy("module2");
//		app.list();
		app.deploy("module-1.0-SNAPSHOT.jar", "module3");
		app.list();
		app.execute("module3");
		app.deploy("module1-1.0-SNAPSHOT.jar", "module4");
		app.execute("module4");
	}
	
}
