package com.InterviewQuestion.TwentryFive;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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



* requirements
Create 10 threads.
Each thread increments the counter 1000 times.
Print final count.
* *
Ans:
WITH ConsecutiveLogins AS (
    SELECT
        user_id,
        login_date,
        LEAD(login_date) OVER (PARTITION BY user_id ORDER BY login_date) as next_login_date
    FROM user_logins
)
SELECT DISTINCT user_id
FROM ConsecutiveLogins
WHERE next_login_date = login_date + INTERVAL 1 DAY; -- Syntax varies slightly by SQL dialect (e.g., DATEDIFF)


/
 */
class Counter {
    private static final AtomicInteger count = new AtomicInteger(0);


    static void main() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++)
        executorService.submit(() -> {
            for (int j = 0; j < 1000; j++) {
                count.incrementAndGet();
            }
        });

        executorService.shutdown();

        try{
            if(executorService.awaitTermination(1, TimeUnit.MINUTES)) {

                System.out.println("updated value of counter is " + count.get());
            }

            else {
                System.out.println("Thread time out before completing execution");
            }

        }
        catch (InterruptedException e)
        {
            System.out.println("Main thread was executed");
        }

    }
}