package com.epam.jmp.tasks.j2ee.hello.ejb;

import javax.ejb.EJB;
import javax.jws.WebService;

@WebService(endpointInterface = "com.epam.jmp.tasks.j2ee.hello.ejb.HelloWebService", serviceName = "HelloWebService")
public class HelloWebServiceImpl implements HelloWebService{

	@EJB
	HelloService helloService;
	
	@Override
	public void hello(String message) {
		helloService.hello(message);
	}

}
