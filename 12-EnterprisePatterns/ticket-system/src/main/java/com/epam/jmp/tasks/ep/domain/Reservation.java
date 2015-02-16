package com.epam.jmp.tasks.ep.domain;

public class Reservation {
	private Ticket ticket;
	private User user;
	
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
