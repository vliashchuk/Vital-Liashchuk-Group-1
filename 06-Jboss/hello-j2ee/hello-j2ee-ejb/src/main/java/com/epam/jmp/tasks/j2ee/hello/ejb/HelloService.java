package com.epam.jmp.tasks.j2ee.hello.ejb;

import javax.ejb.Local;

@Local
public interface HelloService {
	void hello(String message);
}
