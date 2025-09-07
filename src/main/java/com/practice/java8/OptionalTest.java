package com.practice.java8;

import java.util.List;
import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        /*
        * Create an Optional from a nullable value and handle ifPresent
        * */

        String possiblyNull = "Hello Optional";

        Optional<String> optional =Optional.ofNullable(possiblyNull);
        optional.ifPresent(
                value-> System.out.println(value)
        );

        /*
        * Provide a default value using orElse and orElseGet
        * */

      String value = null;
      Optional<String> optional1 = Optional.ofNullable(value);

      String resultOrElse = optional1.orElse("Default Value");
        System.out.println("orElse result: "+resultOrElse);

        String resultOrElseGet = optional1.orElseGet(()->"Lazy default Value");
        System.out.println("orElseGet result: "+resultOrElseGet);

        /*
        * Throw an exception if value is missing using orElseThrow
        * */

        Optional<String> optional2 = Optional.empty();
        try {
            String result = optional2.orElseThrow(() -> new IllegalArgumentException("No Value present"));
        }
        catch (IllegalArgumentException ex){
            System.out.println("Exception caught: "+ex.getMessage());
        }

        /*
        * Filter and map value in Optional
        * */

        Optional<String> optional3 = Optional.of("java8");

        Optional<String>result = optional3.filter(s->s.length()>4)
                .map(String::toUpperCase);

        result.ifPresent(System.out::println);

    }
}
