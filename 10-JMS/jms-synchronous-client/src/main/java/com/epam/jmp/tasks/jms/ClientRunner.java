package com.epam.jmp.tasks.jms;


/**
 * @author Ihar_Karsakou
 *
 */
public final class ClientRunner {

    /**
     * ClientRunner constructor.
     */
    private ClientRunner() {

    }

    /**
     * @param args cmd arguments
     */
    public static void main(String[] args) {
        runJavaImplementation();
    }

    /**
     * Run Java Implementation.
     */
    private static void runJavaImplementation() {
        Client client = new Client();
        client.start();
    }
}
