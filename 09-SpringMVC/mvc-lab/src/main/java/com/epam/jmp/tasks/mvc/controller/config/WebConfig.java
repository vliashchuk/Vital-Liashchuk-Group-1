package com.epam.jmp.tasks.mvc.controller.config;

import java.util.ArrayList;
import java.util.List;

import org.shop.data.Order;
import org.shop.data.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;
import org.springframework.web.servlet.view.tiles2.TilesView;
import org.springframework.web.servlet.view.tiles2.TilesViewResolver;

import com.epam.jmp.tasks.mvc.domain.UserList;
import com.epam.jmp.tasks.mvc.viewresolver.JsonViewResolver;
import com.epam.jmp.tasks.mvc.viewresolver.MarshallingXmlViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

  /**
    * Setup a simple strategy: use all the defaults and return XML by default when not sure. 
    */
  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	  configurer.ignoreAcceptHeader(true)
      	.defaultContentType(MediaType.TEXT_HTML);
  }	
	  /**
	    * Create the CNVR.  Specify the view resolvers to use explicitly.  Get Spring to inject
	    * the ContentNegotiationManager created by the configurer (see previous method).
	    */
	  @Bean
	  public ViewResolver contentNegotiatingViewResolver(
	        ContentNegotiationManager manager) {
	    // Define the view resolvers
	    List<ViewResolver> resolvers = new ArrayList<ViewResolver>();

	    TilesViewResolver r = new TilesViewResolver();
	    r.setContentType("text/html");
	    resolvers.add(r);
//	    InternalResourceViewResolver r1 = new InternalResourceViewResolver();
//	    r1.setPrefix("WEB-INF/jsp/");
//	    r1.setSuffix(".jsp");
//	    resolvers.add(r1);
	    
	    JsonViewResolver r2 = new JsonViewResolver();
	    resolvers.add(r2);
	    
	    
	    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	    Class[] c = {UserList.class, User.class, Order.class};
	    marshaller.setClassesToBeBound(c);
	    MarshallingXmlViewResolver r3 = new MarshallingXmlViewResolver(marshaller);
	    resolvers.add(r3);

	    // Create the CNVR plugging in the resolvers and the content-negotiation manager
	    ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
	    resolver.setViewResolvers(resolvers);
	    resolver.setContentNegotiationManager(manager);
	    return resolver;
	  }
	  
	  @Bean(name = "tilesConfigurer")
		public TilesConfigurer getTilesConfigurer() {
			TilesConfigurer configurer = new TilesConfigurer();
			String[] tilesDefFiles = {"/WEB-INF/tiles/tiles-templates.xml"};
			configurer.setDefinitions(tilesDefFiles);
			return configurer;
		}
}
