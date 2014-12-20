package org.shop;

import org.shop.api.ItemService;
import org.shop.api.OrderService;
import org.shop.api.ProductService;
import org.shop.api.ProposalService;
import org.shop.api.UserService;
import org.shop.context.ApplicationConfiguration;
import org.shop.data.Item;
import org.shop.data.Order;
import org.shop.data.Product;
import org.shop.data.Proposal;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * The ShopLauncher class.
 */
public class ShopLauncher {
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
    	
    	AnnotationConfigApplicationContext context =
    			new AnnotationConfigApplicationContext(
    					ApplicationConfiguration.class);
    	
    	DataInitializer initializer = context
    			.getBean(DataInitializer.class);
    	
    	initializer.initData();
    	
        Product galaxy = context.getBean(ProductService.class)
        		.getProductsByName("Samsung Galaxy Tab").get(0);
        Proposal proposal = context.getBean(ProposalService.class)
        		.getProposalsByProduct(galaxy).get(0);
        
        context.getBean(OrderService.class)
	        .createOrder(
	        		context.getBean(UserService.class).getUserById(1l),
	        		proposal);
        
        for (Order order : context.getBean(OrderService.class)
        		.getOrdersByUserId(1l)) {
            System.out.println(order);
            
            for (Item item : context.getBean(ItemService.class)
            		.getItemsByOrderId(order.getId())) {
            	
                System.out.println(item);
            }
        }
    	
    	context.close();
    }
}
