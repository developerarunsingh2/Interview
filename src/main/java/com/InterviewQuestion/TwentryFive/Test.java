//package com.InterviewQuestion.TwentryFive;
//
//public class Test {
//    You have a REST API:
//    @GetMapping("/users")
//    public List<User> getUsers() {
//        return userService.getUsers();
//    }
//
//    10000,200
//
//
//    The API receives 10,000 concurrent requests.
//}
//
//A database update method is called by multiple threads:
//
//public void updateBalance(Long id, Double amount)
//
//How do you prevent race conditions?
//
//Multiple threads increment a shared counter 1000 times each.
//
//Requirements
//Create 10 threads.
//Each thread increments the counter 1000 times.
//        Print final count.