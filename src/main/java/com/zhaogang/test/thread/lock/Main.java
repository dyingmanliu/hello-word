package com.zhaogang.test.thread.lock;

/**
 * Created by fan.shao on 2017/3/30.
 */
public class Main {
    public static void main(String[] args) {
        PrintQueue printQueue = new LockPrintQueue();
        //PrintQueue printQueue = new SynPrintQueue();
        Thread thread[]=new Thread[10];
        for (int i=0; i<10; i++){
            thread[i] = new Thread(new Job(printQueue),"Thread "+ i);
        }
        for (int i=0; i<10; i++){
            thread[i].start();
        }
    }
}