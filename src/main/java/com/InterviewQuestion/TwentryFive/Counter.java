package com.InterviewQuestion.TwentryFive;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
* Table user_logins which have two columns
- user_id
- login_date

Display the user who logined in two consecutive days
*
*
* */
/*
select user_id from table user_login as u1
join user u2

where user1.user_id = user2.user_id
where diff(u1.login_date-u2.login_date)=1





s
101, 1st, 2nd june.



* equirements
Create 10 threads.
Each thread increments the counter 1000 times.
Print final count.
* */
class Counter implements Runnable{
    private static int count = 0;
 
    public void increment() {
        count++;
    }
 
    public int getCount() {
        return count;
    }


    static void main() {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Counter c = new Counter();
//        Thread t1 = new Thread(c);
        Runnable r = c::increment;
        executorService.submit(r);



        System.out.println(c.getCount());

    }

    @Override
    public void run() {
        this.increment();
    }
}