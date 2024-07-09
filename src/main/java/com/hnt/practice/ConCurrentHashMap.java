package com.hnt.practice;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ConCurrentHashMap {
    public static void main(String args[])
    {
        System.out.println("concurrent HashMap execution");

        Object resource1 = new Object();
        Object resource2 = new Object();

        Thread thread1 = new Thread(()->
        {
            synchronized (resource1){
                System.out.println("Thread 1 acquired resource 1");

                try {
                    Thread.sleep(100);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                synchronized (resource2)
                {
                    System.out.println("Thread 1 acquired resource2");
                }
            }
        });

        Thread thread2 = new Thread(()->
        {
            synchronized (resource2) {
                System.out.println("Thread 2 acquired resource 2");
            }

            synchronized (resource1)
            {
                System.out.println("Thread 2 acquired resource 1");
            }
        });

        thread1.start();
        thread2.start();

        ConcurrentHashMap<String,Integer>map = new ConcurrentHashMap<>();

        Runnable  itr1 =()->{
            for(int i=0;i<1000;i++)
            {
                map.put("Key"+i,i);
            }
        };

        Runnable  itr2 =()->{
            for(int i=1000;i<2000;i++)
            {
                map.put("Key"+i,i);
            }
        };

Thread t1 = new Thread(itr1);
Thread t2= new Thread(itr2);

t1.start();
t2.start();

try{
    t1.join();
    t2.join();
}
catch (InterruptedException e)
{
    e.printStackTrace();
}

System.out.println(map.get("Key500"));

    }
}
