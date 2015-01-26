package com.epam.jmp.tasks.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.shop.api.ProductService;
import org.shop.api.ProposalService;
import org.shop.api.SellerService;
import org.shop.data.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.jmp.tasks.mvc.domain.ProposalDto;

@Controller
@RequestMapping("/proposals")
public class ProposalController {

	private static final Logger LOGGER = Logger.getLogger(ProposalController.class.getName());
	
	@Autowired
	ProposalService proposalService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	SellerService sellerService;
	
//	@RequestMapping(value = "/byseller/{id}", method = RequestMethod.GET)
//	public String list(@PathVariable("id") Long id, Model model){
//		List<Proposal> sellerProposals = proposalService.getProposalsBySellerId(id);
//		model.addAttribute("proposals", new ProposalList(sellerProposals));
//		return "proposals/list";
//	}
	
	@RequestMapping(value = "/forSeller/{id}", method = RequestMethod.GET)
	public String empty(@PathVariable("id") Long id, Model model){

		ProposalDto proposal = new ProposalDto();
		proposal.setSellerId(id);
		model.addAttribute("proposal", proposal);
		model.addAttribute("allProducts", productService.getProducts());
		
		List<Proposal> sellerProposals =
				proposalService.getProposalsBySellerId(proposal.getSellerId());
		model.addAttribute("proposals", sellerProposals);
		model.addAttribute("seller", sellerService
				.getSellerById(proposal.getSellerId()));
		
		return "proposals/detail-new";
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@ModelAttribute("proposal") ProposalDto proposal, Model model){

		proposalService.createProposal(proposal.getSellerId(),
				proposal.getProductId(), proposal.getPrice());
		

		
		LOGGER.debug("Create proposal POST called(id="
					 + proposal.getId()
					 + ", seller id:"
					 + proposal.getSellerId()
					 + ", product id:"
					 + proposal.getProductId()
					 + ", price:"
					 + proposal.getPrice()
					 +")");

		return "redirect:/proposals/forSeller/" + proposal.getSellerId();
	}
	
}
