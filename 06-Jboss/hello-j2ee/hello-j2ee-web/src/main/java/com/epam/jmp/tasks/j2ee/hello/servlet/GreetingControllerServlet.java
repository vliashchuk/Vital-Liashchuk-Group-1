package com.epam.jmp.tasks.j2ee.hello.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.jmp.tasks.j2ee.hello.ejb.GreetingService;
import com.epam.jmp.tasks.j2ee.hello.ejb.domain.Greeting;
import com.epam.jmp.tasks.j2ee.hello.ejb.domain.GreetingEntity;

@WebServlet("/GreetingControllerServlet")
public class GreetingControllerServlet extends HttpServlet
{

	/**
	 * Serial GUID.
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private GreetingService greetingService;
	
    private static String INSERT_OR_EDIT = "/greeting.jsp";
    private static String LIST_GREETING = "/listGreeting.jsp";
    private static String ERROR_MESSAGE = "/error.jsp";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        if(action == null){
        	request.setAttribute("errorMessage", "Action is Null!");
            forward = ERROR_MESSAGE;
        } else  if (action.equalsIgnoreCase("delete")){
            int greetingId = Integer.parseInt(request.getParameter("greetingId"));
            greetingService.deleteGreeting(greetingId);
            forward = LIST_GREETING;
            request.setAttribute("greetings", greetingService.listGreetings());    
        } else if (action.equalsIgnoreCase("listGreetings")){
            forward = LIST_GREETING;
            request.setAttribute("greetings", greetingService.listGreetings());
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int greetingId = Integer.parseInt(request.getParameter("greetingId"));
            Greeting greeting = greetingService.getGreeting(greetingId);
            request.setAttribute("greeting", greeting);
        } else if (action.equalsIgnoreCase("insert")){
            forward = INSERT_OR_EDIT;
        } else {
        	request.setAttribute("errorMessage", "Action '" + action + "' is not supported!");
            forward = ERROR_MESSAGE;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String greetingIdString = request.getParameter("greetingId");
        String greetingText = request.getParameter("greetingText");
        
        if(greetingIdString!=null && greetingIdString.length()>0){
           	int greetingId = Integer.parseInt(greetingIdString);
            GreetingEntity greeting = new GreetingEntity();
            greeting.setId(greetingId);
            greeting.setText(greetingText);
            greetingService.updateGreeting(greeting);
        } else {
        	greetingService.createGreeting(greetingText);
        }
 
        RequestDispatcher view = request.getRequestDispatcher(LIST_GREETING);
        request.setAttribute("greetings", greetingService.listGreetings());
        view.forward(request, response);
    }
	
}
