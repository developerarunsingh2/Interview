package com.practice.multithreading;

public class Test extends Thread {

    @Override
    public void run() {
        System.out.println("run method execution");
    }

    public static void main(String[] args) {
        System.out.println("Hello world !");
        Test obj1 = new Test();
        Thread t1= new Thread(obj1);
        t1.start();
    }
}