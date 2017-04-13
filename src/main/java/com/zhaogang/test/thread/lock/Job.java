package com.zhaogang.test.thread.lock;

/**
 * Created by fan.shao on 2017/3/30.
 */
public class Job implements Runnable {
    private PrintQueue printQueue;

    public Job(PrintQueue printQueue){
        this.printQueue=printQueue;
    }

    @Override
    public void run() {
        System.out.printf("%s: Going to print a document\n", Thread.
                currentThread().getName());
        printQueue.printJob(new Object());
        printQueue.printCount();
        System.out.printf("%s: The document has been printed\n",
                Thread.currentThread().getName());
    }
}
