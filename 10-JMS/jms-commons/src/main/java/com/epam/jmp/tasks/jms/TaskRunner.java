package com.epam.jmp.tasks.jms;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ihar_Karsakou
 *
 */
public class TaskRunner implements Task {

    /**
     *  DEFAULT_CHECK_INTERVAL.
     */
    private static final int DEFAULT_CHECK_INTERVAL = 1000;

    /**
     *  DEFAULT_THREADS_COUNT.
     */
    private static final int DEFAULT_THREADS_COUNT = 1;

    /**
     * Task Factory.
     */
    private TaskFactory taskFactory;
    /**
     * Threads Count.
     */
    private int threadsCount;
    /**
     * Check Interval.
     */
    private int checkInterval;
    /**
     * Is Running.
     */
    private boolean isRunning = false;

    /**
     * @param taskFactory task factory to use.
     */
    public TaskRunner(TaskFactory taskFactory) {
        this.taskFactory = taskFactory;
    }

    /**
     * @return threads count
     */
    public int getThreadsCount() {
        return threadsCount;
    }

    /**
     * @param threadsCount threads count to set.
     */
    public void setThreadsCount(int threadsCount) {
        if (threadsCount > 0) {
            this.threadsCount = threadsCount;
        } else {
            this.threadsCount = DEFAULT_THREADS_COUNT;
        }
    }

    /**
     * @return check interval
     */
    public int getCheckInterval() {
        return checkInterval;
    }

    /**
     * @param checkInterval check interval to set
     */
    public void setCheckInterval(int checkInterval) {
        if (checkInterval > 0) {
            this.checkInterval = checkInterval;
        } else {
            this.checkInterval = DEFAULT_CHECK_INTERVAL;
        }
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        isRunning = true;

        List<Task> tasks = new ArrayList<>();

        while (isRunning()) {

            List<Task> runningTasks = new ArrayList<>();
            for (Task task : tasks) {
                if (task.isRunning()) {
                    runningTasks.add(task);
                }
            }

            if (runningTasks.size() < threadsCount) {
                int toCreate = threadsCount - runningTasks.size();

                for (int i = 0; i < toCreate; i++) {

                    Task task = taskFactory.createTask();
                    runningTasks.add(task);
                    new Thread(task, task.getName()).start();
                }

                tasks = runningTasks;
            }
            try {
                Thread.sleep(checkInterval);
            } catch (InterruptedException e) {
                stop();
            }
        }

        for (Task task : tasks) {
            task.stop();
        }

    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

}
