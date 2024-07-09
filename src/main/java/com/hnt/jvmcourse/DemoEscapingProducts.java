package com.hnt.jvmcourse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DemoEscapingProducts {

    public static Order createOrder(User user, List<Product> products){
        return new Order(LocalDate.now(), products, user);
    }

    public static void processProducts(List<Product> products){
        for(Product product: products)
        {
            System.out.println(product.getName());
        }
    }

    public static void main(String[] args) {
        // create a user
        User user = new User("Maaike", new Address(),new SensitiveObject());


        // create a product list
        List<Product> products = new ArrayList<>();
        products.add(new Product("Java Book","Book for smart java developers",75.0));

        //create an order
        Order o = createOrder(user, products);


        //process products original list
        System.out.println("--original---");
        processProducts(o.getProducts());

        //process products copy list
        List<Product>copy = o.getProducts();
        System.out.println("\n---copy---");
        processProducts(copy);

        //modify new list
        System.out.println("\n---modifying---\n");
        products.get(0).setName("JavaScript book");
        copy.add(new Product("new product","super new",5.0));

        //process products original list
        System.out.println("---original---");
        processProducts(o.getProducts());

        //process products copy list
        System.out.println("\n---copy---");
        processProducts(copy);

    }
}
