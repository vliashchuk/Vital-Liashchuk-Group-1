package com.epam.jmp.tasks.mvc.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.shop.api.ProposalService;
import org.shop.api.SellerService;
import org.shop.data.Proposal;
import org.shop.data.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.jmp.tasks.mvc.domain.SellerList;

@Controller
@RequestMapping("/sellers")
public class SellerController {

	private static final Logger LOGGER = Logger.getLogger(SellerController.class.getName());
	
	@Autowired
	SellerService sellerService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model){

		model.addAttribute("sellers", new SellerList(sellerService.getSellers()));
		return "sellers/list";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String empty(Model model){
		
		model.addAttribute("seller", new Seller());
		return "sellers/detail-new";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model){
		
		model.addAttribute("seller", sellerService.getSellerById(id));
		
		return "sellers/detail";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@ModelAttribute("seller") Seller seller){
		
		sellerService.importSellers(Arrays.asList(seller));
		
		LOGGER.debug("Create seller POST called(id="
					 + seller.getId()
					 + ", name:"
					 + seller.getName()
					 +")");

		return "redirect:/sellers/" + seller.getId();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String update(@ModelAttribute("seller") Seller seller){
		
		sellerService.importSellers(Arrays.asList(seller));
		
		LOGGER.debug("Update seller PUT called(id="
				 + seller.getId()
				 + ", name:"
				 + seller.getName()
				 +")");
		
		return "redirect:/sellers/" + seller.getId();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id, Model model){
		
		sellerService.deleteSeller(id);
		
		LOGGER.debug("Delet seller DELETE called(id="
				 + id
				 +")");
		
		return "redirect:/sellers";
	}
	
}
