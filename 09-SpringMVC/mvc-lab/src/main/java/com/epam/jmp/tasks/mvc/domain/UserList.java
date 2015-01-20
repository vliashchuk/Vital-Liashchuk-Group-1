package com.epam.jmp.tasks.mvc.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.shop.data.User;

@XmlRootElement(name="users")
public class UserList {

	private List<User> users;
	
	public UserList(){}
	public UserList(List<User> users){
		this.users = users;
	}
	@XmlElement(name="user")
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
