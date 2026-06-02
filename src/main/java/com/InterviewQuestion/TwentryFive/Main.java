//tesco interview
package com.InterviewQuestion.TwentryFive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class Main  {

    public static void checkOptional(){
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Apple");

        List<String> allApples =  fruits.stream().filter(v->v.equals("Apple")).collect(toList());

       System.out.println( allApples.size());
    }
    static void main() {
//        List<Integer> l = new ArrayList<>();
//        PrintEven printEven = n -> {
//            while (n % 2 == 0 && n > 2) {
//                l.add(n);
//                n = n / 2;
//            }
//            ;
//
//            return List.of(l.size());
//        }
//    }
//
//    @Override
//    public void run() {
//        System.out.println(n);
//    }
//
int input[] = {0,0,1,1,1,0,0};

int counter = 0;
for(int i:input)
{
    if(i==1)
    {
        counter++;
    }
}

int input1[] = new int[input.length];

for(int i=0;i<counter;i++)
{
    input1[i] = 1;
}
    }
}
