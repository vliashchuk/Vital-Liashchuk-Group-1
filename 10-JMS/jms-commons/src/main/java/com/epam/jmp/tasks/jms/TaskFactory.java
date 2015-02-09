package com.epam.jmp.tasks.jms;

/**
 * @author Ihar_Karsakou
 *
 */
public interface TaskFactory {

    /**
     * @return created task.
     */
    Task createTask();
}
