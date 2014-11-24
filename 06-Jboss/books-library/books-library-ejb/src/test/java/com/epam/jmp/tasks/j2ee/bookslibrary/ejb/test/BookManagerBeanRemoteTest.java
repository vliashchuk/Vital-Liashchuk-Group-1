package com.epam.jmp.tasks.j2ee.bookslibrary.ejb.test;

import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.testng.annotations.Test;

import com.epam.jmp.tasks.j2ee.bookslibrary.ejb.Book;
import com.epam.jmp.tasks.j2ee.bookslibrary.ejb.BookManager;
import com.epam.jmp.tasks.j2ee.bookslibrary.ejb.SimpleBook;

public class BookManagerBeanRemoteTest {

    private static Context getContext() throws NamingException {
        Hashtable<String, Object> p = new Hashtable<String, Object>();
        p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        p.put("jboss.naming.client.ejb.context", true);
        p.put(Context.PROVIDER_URL, "remote://127.0.0.1:4447/");
        p.put(InitialContext.SECURITY_PRINCIPAL, "karsakou");
        p.put(InitialContext.SECURITY_CREDENTIALS, "0");
        p.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
        final Context context = new InitialContext(p);
//        Properties jndiProps = new Properties();
//        jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,         
//        "org.jboss.naming.remote.client.InitialContextFactory");
//        jndiProps.put(Context.PROVIDER_URL,"remote://127.0.0.1:4447/");
//        jndiProps.put(Context.SECURITY_PRINCIPAL, "karsakou");
//        jndiProps.put(Context.SECURITY_CREDENTIALS, "0");
//        jndiProps.put("jboss.naming.client.ejb.context", true);
//        Context context = new InitialContext(jndiProps);
    	
        return context;
    }
	
    @Test
	public void testRmoteCall() throws NamingException{
    	BookManager manager = (BookManager) getContext().lookup("books-library-ejb-1.0-SNAPSHOT/BookManagerBean!com.epam.jmp.tasks.j2ee.bookslibrary.ejb.BookManager");
    	manager.addBook(new SimpleBook(1));
    	manager.addBook(new SimpleBook(2));
    	List<Book> books = manager.listBooks();
    	for(Book book:books){
    		System.out.println(book);
    	}
    	manager.removeBook(2);
    	books = manager.listBooks();
    	for(Book book:books){
    		System.out.println(book);
    	}
    }
	
}
