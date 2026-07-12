package com.practice.multithreading;

public class ThreadMethodExecution implements  Runnable{

    int objectNumber = 0;
    @Override
    public void run() {
        System.out.println("run method : "+ objectNumber);
    }

    ThreadMethodExecution(int objectNumber)
    {
        this.objectNumber = objectNumber;
    }

    public static void main(String[] args) {
        ThreadMethodExecution obj1 = new ThreadMethodExecution(1);
        ThreadMethodExecution obj2 = new ThreadMethodExecution(2);

        Thread t1= new Thread(obj1);
        Thread t2= new Thread(obj2);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();

    }
}
