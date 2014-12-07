package com.epam.jmp.tasks.j2ee.hello.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface HelloWebService {
	@WebMethod
	void hello(String message);
}
