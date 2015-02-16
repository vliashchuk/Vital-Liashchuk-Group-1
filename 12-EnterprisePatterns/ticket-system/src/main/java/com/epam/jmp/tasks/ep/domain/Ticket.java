package com.epam.jmp.tasks.ep.domain;

import java.math.BigDecimal;

import com.epam.jmp.tasks.ep.domain.schedule.Show;

public class Ticket {

	private Show show;
	private Seat seat;
	private BigDecimal price;
	
	public Show getShow() {
		return show;
	}
	public void setShow(Show show) {
		this.show = show;
	}
	public Seat getSeat() {
		return seat;
	}
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	
}
