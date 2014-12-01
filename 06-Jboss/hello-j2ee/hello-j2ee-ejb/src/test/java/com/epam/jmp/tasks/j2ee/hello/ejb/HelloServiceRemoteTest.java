package com.epam.jmp.tasks.j2ee.hello.ejb;

import javax.naming.NamingException;

import org.testng.annotations.Test;

public class HelloServiceRemoteTest extends AbstractRemoteTest{

	@Test
	public void test() throws NamingException{
		HelloServiceRemote helloService = (HelloServiceRemote) super.getContext().lookup("hello-j2ee-ear/hello-j2ee-ejb-1.0-SNAPSHOT/HelloServiceBean!com.epam.jmp.tasks.j2ee.hello.ejb.HelloServiceRemote");
		helloService.hello("Hello from remote client!");
	}
}
