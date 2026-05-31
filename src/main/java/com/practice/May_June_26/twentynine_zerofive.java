package com.practice.May_June_26;

public class twentynine_zerofive {
    static void updateValue(Integer i) {
        i = i + 1;    // i+1 = 31, auto-unboxed then re-boxed to a NEW Integer(31)
        // local parameter 'i' now points to new object Integer(31)
        // ORIGINAL reference outside is unaffected
    }
    public static void main(String[] args) {
        Integer i = 30;   // i points to cached Integer object with value 30
        updateValue(i);   // the reference 'i' is passed by VALUE
        System.out.println(i);
    }
}
