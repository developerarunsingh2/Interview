package com.hnt.revision;

@FunctionalInterface
interface Multiplication {
    int operate(int a, int b);

    static Multiplication multiply() {
        return (a, b) -> a * b;
    }
}



