package com.epam.jmp.tasks.j2ee.hello.ejb;

import javax.ejb.Remote;

@Remote
public interface HelloServiceProducerRemote {
	void hello(String message);
}
