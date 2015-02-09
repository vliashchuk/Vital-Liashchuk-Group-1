package com.epam.jmp.tasks.jms;

/**
 * @author Ihar_Karsakou
 *
 */
public class RequestHandler {

    /**
     * @param request request to handle
     * @return response
     */
    public String handle(String request) {

        return new StringBuffer()
                .append("Hello, ")
                .append(request)
                .append(". It's ")
                .append(Thread.currentThread().getName())
                .append(" ")
                .append(Thread.currentThread().getId()).toString();
    }

}
