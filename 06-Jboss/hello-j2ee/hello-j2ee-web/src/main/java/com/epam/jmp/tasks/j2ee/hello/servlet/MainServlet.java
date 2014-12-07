package com.epam.jmp.tasks.j2ee.hello.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;

import com.epam.jmp.tasks.j2ee.hello.ejb.GreetingService;
import com.epam.jmp.tasks.j2ee.hello.ejb.domain.Greeting;

@WebServlet("/hello")
public class MainServlet extends HttpServlet{

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -6655738586257329430L;

	@EJB
	private GreetingService greetingService;
	
	protected void doGet(HttpServletRequest request,
            			 HttpServletResponse response)
            		throws ServletException, IOException {
		
		List<Greeting> greetings = greetingService.listGreetings();
		
		response.setHeader("Content-Type", "text/html");
		response.getWriter().write("<html><body>");
		response.getWriter().write("<h1>Posted greetings</h1>");
		response.getWriter().write("<ul>");
		for(Greeting greeting:greetings){
			response.getWriter().write("<li>");
			response.getWriter().write(
					StringEscapeUtils.escapeJavaScript(
							StringEscapeUtils.escapeHtml(greeting.getText())));
			response.getWriter().write("</li>");
		}
		response.getWriter().write("</ul>");
		response.getWriter().write("</body></html>");
	}
	
}
