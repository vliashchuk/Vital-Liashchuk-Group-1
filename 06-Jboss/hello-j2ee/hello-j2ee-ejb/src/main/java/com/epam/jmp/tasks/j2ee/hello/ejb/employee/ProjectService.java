package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import javax.ejb.Local;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Project;

@Local
public interface ProjectService extends PersistenceService<Project, Long>{

}
