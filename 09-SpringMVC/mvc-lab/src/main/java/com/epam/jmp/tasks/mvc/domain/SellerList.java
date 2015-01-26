package com.epam.jmp.tasks.mvc.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.shop.data.Seller;

@XmlRootElement(name="sellers")
public class SellerList {
	
	private List<Seller> sellers;

	public SellerList() {
		super();
	}
	public SellerList(List<Seller> sellers) {
		super();
		this.sellers = sellers;
	}
	
	@XmlElement(name="seller")
	public List<Seller> getSellers() {
		return sellers;
	}
	public void setSellers(List<Seller> sellers) {
		this.sellers = sellers;
	}

}
