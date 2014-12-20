package org.shop.context;

import org.shop.api.ItemService;
import org.shop.api.OrderService;
import org.shop.api.ProductService;
import org.shop.api.ProposalService;
import org.shop.api.SellerService;
import org.shop.api.UserService;
import org.shop.api.impl.ItemServiceImpl;
import org.shop.api.impl.OrderServiceImpl;
import org.shop.api.impl.ProductServiceImpl;
import org.shop.api.impl.ProposalServiceImpl;
import org.shop.api.impl.SellerServiceImpl;
import org.shop.api.impl.UserServiceImpl;
import org.shop.repository.ItemRepository;
import org.shop.repository.ProductRepository;
import org.shop.repository.ProposalRepository;
import org.shop.repository.factory.UserRepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Autowired
    private UserRepositoryFactory userRepositoryFactory;
    
    @Autowired
    private ProposalRepository proposalRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ItemRepository itemRepository;    

	@Bean(name = {"userService", "alternateUserServiceName"})
	public UserService userService(){
		return new UserServiceImpl(
				userRepositoryFactory.createUserRepository());
	}
	
	@Bean
	public SellerService sellerService(){
		return new SellerServiceImpl();
	}
	
	@Bean
	public ProposalService proposalService(){
		return new ProposalServiceImpl(proposalRepository,
				sellerService(), productService());
	}
	
	@Bean
	public ProductService productService(){
		return new ProductServiceImpl(productRepository);
	}
	
	@Bean
	public OrderService orderService(){
		return new OrderServiceImpl(itemService());
	} 
	
	@Bean
	public ItemService itemService(){
		return new ItemServiceImpl(itemRepository);
	} 
}
