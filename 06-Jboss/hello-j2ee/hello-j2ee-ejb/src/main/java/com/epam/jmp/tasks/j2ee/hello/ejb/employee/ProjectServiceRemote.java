package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import javax.ejb.Remote;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Project;

@Remote
public interface ProjectServiceRemote extends PersistenceService<Project, Long>{

}
