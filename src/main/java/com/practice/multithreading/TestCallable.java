package com.practice.multithreading;

import java.util.concurrent.*;

public class TestCallable implements Callable<String> {

    private String intro = "call method execution";

    public TestCallable(String intro){
        this.intro = intro;
    }

    @Override
    public String call() throws Exception {
        return intro;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable testCallableObj = new TestCallable("callable method call successfully executed");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(testCallableObj);
        System.out.println(future.get());
    }


}
