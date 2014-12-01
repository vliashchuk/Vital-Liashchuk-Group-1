package com.epam.jmp.tasks.j2ee.hello.ejb;

import org.testng.annotations.Test;

public class HelloServiceBeanTest {

	@Test
	public void test(){
		HelloServiceBean bean = new HelloServiceBean();
		bean.hello("Test HelloServiceBean bean.");
	}
	
}
