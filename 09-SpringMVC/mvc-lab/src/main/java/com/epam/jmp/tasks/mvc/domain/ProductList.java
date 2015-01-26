package com.epam.jmp.tasks.mvc.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.shop.data.Product;

@XmlRootElement(name="products")
public class ProductList {

	private List<Product> products;

	public ProductList(){super();}
	
	public ProductList(List<Product> products) {
		super();
		this.products = products;
	}

	@XmlElement(name="product")
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
}
