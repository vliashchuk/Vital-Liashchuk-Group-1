package com.epam.jmp.tasks.mvc.controller;

import org.apache.log4j.Logger;
import org.shop.api.ProductService;
import org.shop.data.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.jmp.tasks.mvc.domain.ProductList;

@Controller
@RequestMapping("/products")
public class ProductController {

	private static final Logger LOGGER = Logger.getLogger(ProductController.class.getName());
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model){

		model.addAttribute("products", new ProductList(productService.getProducts()));
		return "products/list";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String empty(Model model){
		
		model.addAttribute("product", new Product());
		return "products/detail-new";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model){
		
		model.addAttribute("product", productService.getProductById(id));
		return "products/detail";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@ModelAttribute("product") Product product){
		
		productService.createProduct(product);
		
		LOGGER.debug("Create product POST called(id="
					 + product.getId()
					 + ", name:"
					 + product.getName()
					 +")");

		return "redirect:/products/" + product.getId();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String update(@ModelAttribute("product") Product product){
		
		productService.updateProduct(product);
		
		LOGGER.debug("Update product PUT called(id="
				 + product.getId()
				 + ", name:"
				 + product.getName()
				 +")");
		
		return "redirect:/products/" + product.getId();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id, Model model){
		
		productService.deleteProduct(id);;
		
		LOGGER.debug("Delet product DELETE called(id="
				 + id
				 +")");
		
		return "redirect:/products";
	}
	
}
