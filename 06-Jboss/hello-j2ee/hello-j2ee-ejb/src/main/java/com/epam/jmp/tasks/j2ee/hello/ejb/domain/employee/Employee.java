package com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Enumerated(EnumType.ORDINAL)
	private EmployeeStatus status;
	
	@Embedded
	private Address address;
	
	@OneToOne(mappedBy = "employee")
	private EmployeePersonalInfo personalInfo;
	
	@ManyToOne
	@JoinColumn(name = "unit_id")
	private Unit unit;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name = "employee_projects",
			joinColumns = @JoinColumn(name = "employee_id"),
			inverseJoinColumns = @JoinColumn(name = "project_id"))
	private Set<Project> projects;

	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append("Employee description:").append("\n");
		ret.append("id: ").append(id).append("\n");
		if(personalInfo == null){
			ret.append("Employee personal Info: null").append("\n");
		} else {
			ret.append("Employee personal Info: ").append("\n");
			ret.append("first name: ")
				.append(personalInfo.getFirstName()).append("\n");
			ret.append("last name: ")
			.append(personalInfo.getLastName()).append("\n");
		}
		if(status == null){
			ret.append("Employee status: null").append("\n");
		} else {
			ret.append("Employee status: ").append(status).append("\n");
		}
		if(address == null){
			ret.append("Employee address: null").append("\n");
		} else {
			ret.append("Employee address: ").append("\n");
			ret.append("city: ")
				.append(address.getCity()).append("\n");
			ret.append("street: ")
			.append(address.getStreet()).append("\n");
		}
		if(unit == null){
			ret.append("Employee unit: null").append("\n");
		} else {
			ret.append("Employee unit: ").append("\n")
				.append(unit.toString()).append("\n");
		}
		if(projects == null){
			ret.append("Employee projects: null").append("\n");
		} else {
			ret.append("Employee projects: ").append("\n");
			for(Project project:projects){
				ret.append(project.toString()).append("\n");
			}
		}
		
		return ret.toString();
	}
	
	public EmployeeStatus getStatus() {
		return status;
	}

	public void setStatus(EmployeeStatus status) {
		this.status = status;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public EmployeePersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(EmployeePersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Collection<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
