package org.shop.context;

import org.shop.api.ItemService;
import org.shop.api.UserService;
import org.shop.api.impl.ItemServiceImpl;
import org.shop.api.impl.UserServiceImpl;
import org.shop.repository.ItemRepository;
import org.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.shop.api.impl")
public class ServiceConfiguration {

    @Autowired
    private ItemRepository itemRepository;    
    
    @Autowired
    private UserRepository userRepository;

	@Bean(name = {"userService", "alternateUserServiceName"})
	public UserService userService(){
		
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.populate(userRepository);
		
		return userServiceImpl;
	}
	
	@Bean
	public ItemService itemService(){
		return new ItemServiceImpl(itemRepository);
	} 
}
