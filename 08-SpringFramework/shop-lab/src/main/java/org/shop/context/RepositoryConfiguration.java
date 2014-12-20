package org.shop.context;

import org.shop.repository.ItemRepository;
import org.shop.repository.OrderRepository;
import org.shop.repository.ProductRepository;
import org.shop.repository.ProposalRepository;
import org.shop.repository.SellerRepository;
import org.shop.repository.UserRepository;
import org.shop.repository.factory.UserRepositoryFactory;
import org.shop.repository.map.ItemMapRepository;
import org.shop.repository.map.OrderMapRepository;
import org.shop.repository.map.ProductMapRepository;
import org.shop.repository.map.ProposalMapRepository;
import org.shop.repository.map.SellerMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:resource-config.properties")
public class RepositoryConfiguration {

	@Autowired
	UserRepositoryFactory userRepositoryFactory;
	
	@Autowired
    Environment env;
	
	@Bean
	public UserRepository userRepository(){
		return userRepositoryFactory.createUserRepository();
	}
	
	@Bean
	public SellerRepository sellerRepository(){
		return new SellerMapRepository();
	}
	
	@Bean
	public ProposalRepository proposalRepository(){
		return new ProposalMapRepository();
	}
	
	@Bean
	public ProductRepository productRepository(){
		return new ProductMapRepository();
	}
	
	@Bean
	public OrderRepository orderRepository(){
		OrderMapRepository repo = new OrderMapRepository();
		repo.setSequence(Long.parseLong(env.getProperty("repository.order.pk")));
		return repo;
	}
	
	@Bean
	public ItemRepository itemRepository(){
		return new ItemMapRepository();
	}
	
}
