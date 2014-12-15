package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Employee;
import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Project;

@Stateless
public class ProjectServiceBean implements ProjectService, ProjectServiceRemote{


	private static final String LIST_All_SQL = "SELECT e FROM Project e";
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public Project createProject(Project project) {
		entityManager.persist(project);
		return project;
	}

	@Override
	public void deleteProject(Long id) {
		Project p = entityManager.find(Project.class, id);
		entityManager.remove(p);
	}

	@Override
	public Project getProject(Long id) {
		return entityManager.find(Project.class, id);
	}

	@Override
	public void updateProject(Project project) {
		entityManager.merge(project);
	}

	@Override
	public void addEmployeeToProject(Long employeeId, Long projectId) {
		Employee e = entityManager.find(Employee.class, employeeId);
		Project p = entityManager.find(Project.class, projectId);
		
		assert e != null;
		assert p != null;
		
		e.getProjects().add(p);
		p.getEmployees().add(e);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Project> listAllProjects() {
		return entityManager.createQuery(LIST_All_SQL).getResultList();
	}

}
