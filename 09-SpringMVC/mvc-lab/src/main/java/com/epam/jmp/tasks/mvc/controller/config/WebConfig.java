package com.epam.jmp.tasks.mvc.controller.config;

import java.util.ArrayList;
import java.util.List;

import org.shop.data.Order;
import org.shop.data.Product;
import org.shop.data.Proposal;
import org.shop.data.Seller;
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

import com.epam.jmp.tasks.mvc.domain.ProductList;
import com.epam.jmp.tasks.mvc.domain.ProposalList;
import com.epam.jmp.tasks.mvc.domain.SellerList;
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

	    TilesViewResolver tvr = new TilesViewResolver();
	    tvr.setContentType("text/html");
	    resolvers.add(tvr);
	    
	    JsonViewResolver jvr = new JsonViewResolver();
	    resolvers.add(jvr);
	    
	    
	    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	    Class[] c = {UserList.class,
	    			 User.class,
	    			 SellerList.class,
	    			 Seller.class,
	    			 ProductList.class,
	    			 Product.class,
	    			 Proposal.class,
	    			 ProposalList.class};
	    marshaller.setClassesToBeBound(c);
	    MarshallingXmlViewResolver xvr = new MarshallingXmlViewResolver(marshaller);
	    resolvers.add(xvr);

	    // Create the CNVR plugging in the resolvers and the content-negotiation manager
	    ContentNegotiatingViewResolver cnvr = new ContentNegotiatingViewResolver();
	    cnvr.setViewResolvers(resolvers);
	    cnvr.setContentNegotiationManager(manager);
	    return cnvr;
	  }
	  
	  @Bean(name = "tilesConfigurer")
		public TilesConfigurer getTilesConfigurer() {
			TilesConfigurer configurer = new TilesConfigurer();
			String[] tilesDefFiles = {"/WEB-INF/tiles/tiles-templates.xml"};
			configurer.setDefinitions(tilesDefFiles);
			return configurer;
		}
}
