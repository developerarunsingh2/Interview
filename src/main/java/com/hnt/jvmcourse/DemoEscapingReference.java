package com.hnt.jvmcourse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DemoEscapingReference {
    public static void sendOrder(Order order) {
        System.out.println("Sending odrder...\n");
        System.out.println("Date: "+order.getOrderDate());
        System.out.println("User: "+order.getUser().getName());
        System.out.println("Products: ");

        for(Product p: order.getProducts())
        {
            System.out.println("- "+p.getName());
        }

        order.getUser().setSensitiveObject(new SensitiveObject());
    }

    public static void main(String[] args) {
        //create a user

        SensitiveObject so = new SensitiveObject();
        so.setSecretCode(123456);
        User user = new User("Maaike",new Address(),so);

        //create a product list
        List<Product> products = new ArrayList<>();
        products.add(new Product("Java book","Book for smart Java developers",75.0));

        //create an order
        Order o = new Order(LocalDate.now(),products,user);
        sendOrder(o);
        System.out.println(o.getUser().getSensitiveObject().getSecretCode());
    }
}
