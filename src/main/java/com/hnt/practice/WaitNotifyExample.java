package com.hnt.practice;

import static java.lang.System.out;

public class WaitNotifyExample {
    public static void main(String[] args) {
        Message message = new Message();
        // Create a thread to produce a message
        Thread producerThread = new Thread(new Message.Producer(message), "Producer");
        Thread consumerThread = new Thread(new Message.Consumer(message), "Consumer");
        producerThread.start();
        consumerThread.start();
    }

    static class Message {
        private String content;
        private boolean empty = true;

        // Method to read the message
        public synchronized String read() {
            while (empty) {
                try {
                    // wait until message is available
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Consume the message
            empty = true;
            notify(); // Notify producer message has been consumed
            return content;
        }

        // Method to write the message
        public synchronized void write(String content) {
            while (!empty) {
                try {
                    // wait until the message is consumed
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            empty = false;
            this.content = content;
            notify(); // notify consumer that message is available
        }

        static class Producer implements Runnable {
            private final Message message;

            public Producer(Message message) {
                this.message = message;
            }

            @Override
            public void run() {
                String[] messages = {"Message 1", "Message 2", "Message 3", "End"};

                for (String msg : messages) {
                    message.write(msg);
                    out.println(Thread.currentThread().getName() + " wrote: " + msg);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Consumer class
        static class Consumer implements Runnable {
            private final Message message;

            Consumer(Message message) {
                this.message = message;
            }

            @Override
            public void run() {
                String msg;

                do {
                    msg = message.read();

                    if (!msg.equals("End")) {
                        out.println(Thread.currentThread().getName() + " read: " + msg);
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (!msg.equals("End"));
            }
        }
    }
}
