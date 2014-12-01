package com.epam.jmp.tasks.j2ee.hello.ejb;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

@Stateless
public class HelloServiceBean implements HelloService, HelloServiceRemote{
	
	private static final Logger LOGGER = Logger.getLogger(HelloServiceBean.class);
	
	@Override
	public void hello(String message) {
		LOGGER.info(message);
	}

}
