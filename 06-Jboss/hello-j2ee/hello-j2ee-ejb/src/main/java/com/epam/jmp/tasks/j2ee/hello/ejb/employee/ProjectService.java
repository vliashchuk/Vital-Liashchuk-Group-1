package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import java.util.Collection;

import javax.ejb.Local;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Project;

@Local
public interface ProjectService {
	
	Project createProject(Project project);

	void deleteProject(Long id);

	Project getProject(Long id);

	void updateProject(Project project);

	void addEmployeeToProject(Long employeeId, Long projectId);
	
	Collection<Project> listAllProjects();
}
