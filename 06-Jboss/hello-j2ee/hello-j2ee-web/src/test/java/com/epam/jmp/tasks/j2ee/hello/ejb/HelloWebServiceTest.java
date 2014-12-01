package com.epam.jmp.tasks.j2ee.hello.ejb;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.testng.annotations.Test;

public class HelloWebServiceTest {

	@Test
	public void test(){
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(HelloWebService.class);
		factory.setAddress("http://localhost:8080/web/HelloWebService");
		
		HelloWebService client = (HelloWebService) factory.create();
		client.hello("Hello from web service client!");
		
	}
	
}
