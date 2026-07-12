package com.practice.multithreading;

public class TestRunnable implements  Runnable{
    @Override
    public void run() {
        System.out.println("run method execution");
    }

    public static void main(String[] args) {
        TestRunnable testRunnableObj = new TestRunnable();
        Thread t1 = new Thread(testRunnableObj);
        t1.start();
    }

}
