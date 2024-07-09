package com.hnt.practice;

public class MyRunnable  implements Runnable{
    private final String name;
    private final SynchronizationExmaple.SharedObject sharedObject;

    public String getName() {
        return name;
    }


    public MyRunnable(String name, SynchronizationExmaple.SharedObject sharedObject) {
        this.name = name;
        this.sharedObject = sharedObject;
    }




    @Override
    public String toString() {
        return "MyRunnable{" +
                "name='" + name + '\'' +
                ", sharedObject=" + sharedObject +
                '}';
    }

    public void run()
    {
        for(int i=0;i<5;i++)
        {
            System.out.println(name + " : "+i);

            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
