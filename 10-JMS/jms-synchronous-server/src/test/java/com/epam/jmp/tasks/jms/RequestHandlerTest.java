package com.epam.jmp.tasks.jms;

import org.testng.Assert;
import org.testng.annotations.Test;



public class RequestHandlerTest {

    @Test
    public void test(){
        RequestHandler handler = new RequestHandler();
        String request = "request";
        String response = handler.handle(request);
        Assert.assertTrue(response.matches("Hello, " + request +". It's *"));
    }
    
}
