package com.hnt.jvmcourse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private LocalDate orderDate;
    private List<Product> products;
    private User user;

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public List<Product> getProducts() {
//        List<String> copy = new ArrayList<>(products);
//        return copy;
//        return List.copyOf(products);
//        return Collections.unmodifiableList(products);

//        deep copy
        List<Product> copy = new ArrayList<>();
        for (Product p : products) {
            try {
                copy.add((Product) p.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        return copy;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        try{
            return (User)user.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order(LocalDate orderDate, List<Product> products, User user) {
        this.orderDate = orderDate;
        this.products = new ArrayList<>(products);
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderDate=" + orderDate +
                ", products=" + products +
                ", user=" + user +
                '}';
    }
}
