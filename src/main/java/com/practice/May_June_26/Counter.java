package com.practice.May_June_26;

import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        // 1. Acquire the lock
        lock.lock(); 
        try {
            // 2. Critical section: access shared resource
            count++;
            System.out.println(Thread.currentThread().getName() + " updated count to: " + count);
        } finally {
            // 3. Always release the lock in a finally block
            lock.unlock(); 
        }
    }}

/*Key Features of ReentrantLockReentrancy: If a thread already holds the lock,
it can call lock() again on the same object. This increments a "hold count,"
which must be decremented back to zero via unlock() calls before other threads can take the lock.
Fairness: You can create a "fair" lock by passing true to the constructor: new ReentrantLock(true).
This grants the lock to the thread that has been waiting the longest (FIFO order).
Non-blocking Attempts: Using tryLock() allows a thread to check if the lock is available
 and perform other tasks if it is busy, rather than freezing indefinitely.
 Interruptible Locks: Unlike synchronized, threads waiting for a ReentrantLock can be interrupted
  using lockInterruptibly(), allowing for better responsiveness in complex
  systems.Common MethodsMethodDescriptionlock()Acquires the lock, blocking if another thread holds it
  .unlock()Releases the lock held by the current thread.tryLock()Returns true if the lock was acquired,
   false otherwise.getHoldCount()Returns the number of times the current thread has acquired
   the lock.isLocked()Checks if any thread currently holds the lock.
* */