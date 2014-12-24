package org.shop.context;

import org.shop.repository.UserRepository;
import org.shop.repository.factory.UserRepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:/resource-config.properties")
@ComponentScan(basePackages = "org.shop.repository.map")
public class RepositoryConfiguration {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
	    return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Autowired
	UserRepositoryFactory userRepositoryFactory;
	
	@Bean
	public UserRepository userRepository(){
		return userRepositoryFactory.createUserRepository();
	}
	
}
