package com.zhaogang.test.thread.lock;

/**
 * Created by fan.shao on 2017/3/30.
 */

public class SynPrintQueue implements PrintQueue{

    private int count = 0;

    // 实现printJob()方法，它将接收Object对象作为参数，并且不会返回任何值。
    public synchronized void printJob(Object document) {
        try {
            Long duration=(long)(Math.random()*1000);
            Thread.sleep(duration);
            System.out.println(Thread.currentThread().getName()
                    + ": SynPrintQueue: Printing a Job during " + (duration/1000)+ " seconds");
            count++;
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printCount() {
        System.out.println("count:" + count);
    }
}