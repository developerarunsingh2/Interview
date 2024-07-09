package com.hnt.practice;

public class SynchronizationExmaple {
    public static void main(String[] args) throws InterruptedException {
        SharedObject sharedObject = new SharedObject();
        // create multiple thread that access the shared object

        Thread thread1 = new Thread(new MyRunnable("Thread1",sharedObject));
        Thread thread2 = new Thread(new MyRunnable("Thread2",sharedObject));

        thread1.start();
//        thread1.join();
        thread2.start();
    }


    static class SharedObject {
        private  int counter = 0;

        public synchronized  void increment(){
            counter++;
            System.out.println(Thread.currentThread().getName()+": Counter is now: "+counter);

        }
    }
}