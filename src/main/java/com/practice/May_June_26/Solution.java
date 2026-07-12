package com.practice.May_June_26;

record User(String name, int age, String email){
  public User{

      if(age<10){
          throw new IllegalArgumentException("person is below 10 years");

      }
  }
}

public class Solution
{
    static void main() {
        User user1 = new User("Ram",1,"ram@gmail.com");
        User user2 = new User("Shiv",11,"shiv@gmail.com");

        System.out.println("user1 is: " +user1);
    }
}

