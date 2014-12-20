package org.shop.context;

import java.util.HashMap;
import java.util.Map;

import org.shop.DataInitializer;
import org.shop.ProductInitializer;
import org.shop.ProposalInitializer;
import org.shop.SellerInitializer;
import org.shop.UserInitializer;
import org.shop.api.ItemService;
import org.shop.api.OrderService;
import org.shop.api.ProductService;
import org.shop.api.ProposalService;
import org.shop.api.SellerService;
import org.shop.api.UserService;
import org.shop.common.Sellers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitializerConfiguration {

	@Autowired
	UserService userService;
	
	@Autowired
	SellerService sellerService;

	@Autowired
	ProposalService proposalService;

	@Autowired
	ProductService productService;

	@Autowired
	OrderService orderService;

	@Autowired
	ItemService itemService;
	
	@Bean
	public DataInitializer dataInitializer(){
		DataInitializer dataInitializer = new DataInitializer();
		
		dataInitializer.setProductInitializer(
				new ProductInitializer(productService));
		
		dataInitializer.setProposalInitializer(
				new ProposalInitializer(
						productService,
						proposalService,
						sellerService));
		
		
        Map<Long, String> map = new HashMap<Long, String>();
        map.put(new Long(1), Sellers.AMAZON);
        map.put(new Long(2), Sellers.SAMSUNG);
        map.put(new Long(3), "Apple");
		
		dataInitializer.setSellerInitializer(
				new SellerInitializer(sellerService, map));
		
		dataInitializer.setUserInitializer(
				new UserInitializer(userService));

		
		return dataInitializer;
	}
	
}
