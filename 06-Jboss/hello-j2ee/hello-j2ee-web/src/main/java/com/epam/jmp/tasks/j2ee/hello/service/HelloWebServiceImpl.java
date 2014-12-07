package com.epam.jmp.tasks.j2ee.hello.service;

import javax.ejb.EJB;
import javax.jws.WebService;

import com.epam.jmp.tasks.j2ee.hello.ejb.HelloService;

@WebService(endpointInterface = "com.epam.jmp.tasks.j2ee.hello.service.HelloWebService", serviceName = "HelloWebService")
public class HelloWebServiceImpl implements HelloWebService{

	@EJB
	HelloService helloService;
	
	@Override
	public void hello(String message) {
		helloService.hello(message);
	}

}
