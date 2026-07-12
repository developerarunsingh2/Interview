package com.ey;

import org.springframework.core.task.VirtualThreadTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArrayBlockingQueueImpl  {
    public ArrayBlockingQueueImpl() throws ExecutionException, InterruptedException {
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int a = 10;
        int b = 123;

        System.out.println("Testing the code");
        List<ArrayList<String>> list = new ArrayList<>();
        Thread t = new Thread(() -> System.out.println("this is ram"));
        t.start();

        VirtualThreadTaskExecutor service = new VirtualThreadTaskExecutor();
        service.execute(() -> System.out.println("This is virtual thread"));

  /*      ExecutorService serviceExecutoer = (ExecutorService) Thread.ofVirtual().start(() ->
        {
            for (int i = 0; i < 10; i++) {
                System.out.println("value of i is: " + i);
            }

        });
//*/
//        Executors.newVirtualThreadPerTaskExecutor();
//        serviceExecutoer.execute(() -> {
//            for (int i = 0; i < 10; i++)
//                System.out.println("this is input" + i);
//        });


// virtual thread...

        int arr[] = {1, 3, 4, 2, -6, -7, 8};

        int max = arr[0];
        int sum =arr[0];

        for (int i = 0; i < arr.length; i++) {

            if(sum<0)
            {
                sum = arr[i];
            }

            else {
                sum = sum + arr[i];
            }

            max = Math.max(sum,max);
        }

        System.out.println("Maximum value is: "+max);

        ExecutorService exec = Executors.newFixedThreadPool(4);
        Future<Integer> future = exec.submit(() -> 42);
        Integer result = future.get();  // blocks until done

        System.out.println("using executors fixed thread pull: "+result);

        Thread vt = Thread.ofVirtual().start(() -> System.out.println("Virtual!"));

//// Or via executor:
//        try (var exec = Executors.newVirtualThreadPerTaskExecutor()) {
//            exec.submit(() -> doBlockingIO());
//        }

        //program to find duplicate characer:

        String str = "ArunKumar";
     Optional<Map.Entry<Character, Long>> chara=   str.chars().mapToObj(c->(char) c).collect(
                Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                )
        ).entrySet()
                .stream()
                .filter(p->p.getValue()>1)
                .findFirst();

        System.out.println("first repeating character is:" +chara.get());

    }
}
