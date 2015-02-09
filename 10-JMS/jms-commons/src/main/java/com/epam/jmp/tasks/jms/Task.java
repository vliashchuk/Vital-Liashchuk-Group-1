package com.epam.jmp.tasks.jms;

/**
 * @author Ihar_Karsakou
 *
 */
public interface Task extends Runnable {

    /**
     * @return true if task is running.
     */
    boolean isRunning();

    /**
     * Stops task.
     */
    void stop();

    /**
     * @return name of task.
     */
    String getName();

}
