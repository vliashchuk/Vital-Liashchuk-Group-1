package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import javax.ejb.Stateless;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Project;

@Stateless
public class ProjectServiceBean extends PersistenceServiceImpl<Project, Long> implements ProjectService, ProjectServiceRemote {

	public ProjectServiceBean() {
		super(Project.class);
	}

}
