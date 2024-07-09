package com.hnt.practice;

public class Multithreading {

    public static void main(String[] args) throws InterruptedException {

        SynchronizationExmaple.SharedObject  sharedObject = new SynchronizationExmaple.SharedObject();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread t1 = new Thread(() -> {
                System.out.println("Thread 1 Started "+ finalI);
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
              //  Thread.sleep(1000);

            });

          Thread t2 = new Thread(()->{
              try
              {
                  System.out.println("Thread 2 Started "+ finalI);
                  Thread.sleep(1000);
              }
              catch (InterruptedException e)
              {
                  e.printStackTrace();
              }
          });

          t1.start();
          t1.join();
          t2.start();
        }

        Thread t3 = new Thread(new MyRunnable("ram", sharedObject));
        Thread t4 = new Thread(new MyRunnable("shiva", sharedObject));

        t3.start();
        t4.start();

        MyRunnable o1 = new MyRunnable("Rahul", sharedObject);
        MyRunnable o2 = new MyRunnable("Reddy", sharedObject);
        synchronized (o1)
        {
            Thread t5 = new Thread(o1);
            System.out.println("Thread started with name : "+ o1.getName());
        }

        Thread t5 = new Thread(o1);
        Thread t6 = new Thread(o2);

        t5.start();
        t6.start();
    }
}
