package com.zhaogang.test.thread.actomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by fan.shao on 2017/3/30.
 */
public class CounterAtomic implements Runnable {
    private final AtomicInteger count = new AtomicInteger(0);

    public void run() {
        System.out.println(Thread.currentThread().getName()
                + ":" + count.incrementAndGet());
    }

    public static void main(String[] args){
        CounterAtomic counter = new CounterAtomic();

        Thread thread[]=new Thread[100];
        for (int i=0; i<100; i++){
            thread[i] = new Thread(counter);
        }
        for (int i=0; i<100; i++){
            thread[i].start();
        }
    }
}
