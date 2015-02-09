package com.epam.jmp.tasks.jms;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Ihar_Karsakou
 *
 */
public final class ServerRunner {

    /**
     * Default constructor.
     */
    private ServerRunner() {
    }

    /**
     * @param args
     *            cmd argumants.
     */
    public static void main(String[] args) {

        if (args != null && args.length > 0 && args[0].equals("spring")) {
            runSpringImplementation();
        } else {
            runJavaImplementation();
        }
    }

    /**
     * Run spring implementation.
     */
    private static void runSpringImplementation() {
        new ClassPathXmlApplicationContext("server-context.xml");
    }

    /**
     * Run java implementation.
     */
    private static void runJavaImplementation() {
        Server server = new Server();
        server.start();
    }
}
