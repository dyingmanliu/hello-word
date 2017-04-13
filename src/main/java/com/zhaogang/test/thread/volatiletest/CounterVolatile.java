package com.zhaogang.test.thread.volatiletest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by fan.shao on 2017/3/30.
 */
public class CounterVolatile {

    public static volatile int count = 0;

    public static void inc() {
        //这里延迟1毫秒，使得结果明显
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }
        count++;
    }

    public static void main(String[] args) {

        //同时启动1000个线程，去进行i++计算，看看实际结果
        List<Thread> list = new ArrayList<Thread>();
        for (int i = 0; i < 1000; i++) {
            list.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    CounterVolatile.inc();
                }
            }));
        }
        for (Thread my : list)
        {
            my.start();
        }
        try {
            for (Thread my : list) {
                my.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:CounterAtomic.count=" + CounterVolatile.count);
    }
}
