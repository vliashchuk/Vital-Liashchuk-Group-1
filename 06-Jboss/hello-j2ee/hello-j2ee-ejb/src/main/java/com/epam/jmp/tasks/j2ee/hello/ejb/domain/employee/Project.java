package com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Project implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	private String name;
	
	@ManyToMany(mappedBy="projects", fetch=FetchType.EAGER)
	private Set<Employee> employees;

	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("Project description:").append("\n");
		ret.append("id: ").append(id).append("\n");
		ret.append("name: ").append(name).append("\n");
		if(employees==null){
			ret.append("employees: null").append("\n");
		}else {
			ret.append("employees count: ").append(employees.size()).append("\n");
		}
		return ret.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
