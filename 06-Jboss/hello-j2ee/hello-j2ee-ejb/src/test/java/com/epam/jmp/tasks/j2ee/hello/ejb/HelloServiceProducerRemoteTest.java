package com.epam.jmp.tasks.j2ee.hello.ejb;

import javax.naming.NamingException;

import org.testng.annotations.Test;

public class HelloServiceProducerRemoteTest extends AbstractRemoteTest{

	@Test
	public void test() throws NamingException{
		HelloServiceProducerRemote helloService = (HelloServiceProducerRemote) super.getContext().lookup("hello-j2ee-ear/hello-j2ee-ejb-1.0-SNAPSHOT/HelloServiceProducerBean!com.epam.jmp.tasks.j2ee.hello.ejb.HelloServiceProducerRemote");
		helloService.hello("Hello from remote client by queued message!");
	}
	
}
