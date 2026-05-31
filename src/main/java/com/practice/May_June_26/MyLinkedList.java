package com.practice.May_June_26;

import java.util.NoSuchElementException;

public class MyLinkedList<T> {

    // ── Node class ────────────────────────────────
    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; this.next = null; }
    }

    private Node<T> head;
    private int size;

    // ── Add to end ────────────────────────────────
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) { head = newNode; }
        else {
            Node<T> curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = newNode;
        }
        size++;
    }

    // ── Add at head (O(1)) ────────────────────────
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // ── Get by index ──────────────────────────────
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<T> curr = head;
        for (int i = 0; i < index; i++) curr = curr.next;
        return curr.data;
    }

    // ── Remove head ───────────────────────────────
    public T removeFirst() {
        if (head == null) throw new NoSuchElementException();
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }

    // ── Reverse in-place ──────────────────────────
    public void reverse() {
        Node<T> prev = null, curr = head, next = null;
        while (curr != null) {
            next = curr.next;  // save next
            curr.next = prev;  // reverse pointer
            prev = curr;       // move prev forward
            curr = next;       // move curr forward
        }
        head = prev;
    }

    public int size() { return size; }

    public void print() {
        Node<T> curr = head;
        while (curr != null) {
            System.out.print(curr.data + (curr.next != null ? "->" : ""));
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("Ram");
        list.add("Sita");
        System.out.println("manual list is: " +list.size);

    }
}
