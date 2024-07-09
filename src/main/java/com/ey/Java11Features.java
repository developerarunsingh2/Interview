package com.ey;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

public class Java11Features {


    public static void main(String[] args) throws Exception {
        /*
         * Local variable syntax for lambda expression before java 11
         * */

        Function<Integer, Integer> increment = (Integer x) -> x + 1;
        System.out.println(increment.apply(12));

        /*
         * Local variable syntax for lambda expression after java 11
         * */

        Function<Integer,Integer> increment11 = (var y)->y+1;
        System.out.println(increment11.apply(15));

        /*Java 11 introduces a new HTTP client API  to make HTTP Requests */

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest  request= HttpRequest.newBuilder()
                .uri(new URI("https://jsonplaceholder.typicode.com/posts/1"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

/*Java 11 string methods */
        String str = "Hello, World!   ";

        //Strip leading and trailing white spaces
        String trimmed = str.strip();
        System.out.println(trimmed);

        //string leading white spaces
        String leftTrimmed = str.stripLeading();
        System.out.println(leftTrimmed);
        String rightTrimmed= str.stripTrailing();
        System.out.println(rightTrimmed);
        //repeat a string n times

        String repeated = "abc".repeat(3);
        System.out.println(repeated);
        // Check if a string is blank(contains only white space)
        boolean isBlank  = str.isBlank();
        System.out.println(isBlank);

        /*Java 11 introduced several useful methods to the 'Collection' interface*/

        List<String> list = List.of("apple","banana","cherry");
        List<Character> characterList = List.of('a','b');

        //Convert Collection to an array

        String[] array = list.toArray(String[]::new);

        //Create an unmodifiable list copy
        List<String> copy = List.copyOf(list);

        // RemoveIf method
        list.removeIf(s->s.startsWith("a"));
        System.out.println(list);

    }

    /*Nest Based access control
     * allows classes that are logically part of the same code entity to access each other's
     * private members without the need for compilers to insert accessibility-broadening bridge methods*/

    public class Outer{
        private int x=10;
        public class Inner{
            private int y=20;

            public void accessPrivateMembers(){
                System.out.println("Accessing private members: "+x+","+y);
            }
        }

    }

    }
