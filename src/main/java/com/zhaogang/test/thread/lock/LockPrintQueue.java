package com.zhaogang.test.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by fan.shao on 2017/3/30.
 */

public class LockPrintQueue implements PrintQueue{

    // 声明一个Lock对象，并且使用ReentrantLock类的一个新对象来初始化它
    private final Lock queueLock = new ReentrantLock();

    private int count = 0;

    // 实现printJob()方法，它将接收Object对象作为参数，并且不会返回任何值。
    public void printJob(Object document) {
        /*
        boolean hasLock = queueLock.tryLock();
        if(!hasLock) {
            System.out.println(Thread.currentThread().getName()
                    + ": LockPrintQueue: Waiting for lock ");
            queueLock.lock();
        }*/
        queueLock.lock();
        try {
            Long duration=(long)(Math.random()*1000);
            Thread.sleep(duration);
            System.out.println(Thread.currentThread().getName()
                    + ": LockPrintQueue: Printing a Job during " + (duration/1000)+ " seconds");
            count++;
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }
    }

    public void printCount() {
        System.out.println("count:" + count);
    }
}