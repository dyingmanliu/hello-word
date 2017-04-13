package com.zhaogang.test.thread.threadlocal;

/**
 * Created by fan.shao on 2017/3/30.
 */
public class ThreadLocalExample {

    public static class MyRunnable implements Runnable {

        private ThreadLocal<Integer> threadLocal =
                new ThreadLocal<Integer>(){
                    @Override protected Integer initialValue() {
                        return 1;
                    }
                };

        private ThreadLocal<Integer> threadLocal2 =
                new ThreadLocal<Integer>(){
                    @Override protected Integer initialValue() {
                        return 1;
                    }
                };

        private Integer noLocal = 1;

        @Override
        public void run() {
            int random = (int) (Math.random() * 100D);
            threadLocal.set(random);
            noLocal = random;
            System.out.println(Thread.currentThread().getName() + " set value:" + random);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            System.out.println(Thread.currentThread().getName() + " get value:" + threadLocal.get());
            System.out.println(Thread.currentThread().getName() + " no local value:" + noLocal);
        }
    }

    public static void main(String[] args) throws Exception{
        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();
        thread2.start();

        thread1.join(); //wait for thread 1 to terminate
        thread2.join(); //wait for thread 2 to terminate
    }

}