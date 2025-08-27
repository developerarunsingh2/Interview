package com.practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Interview {
    public static void main(String[] args) {
        List<String> al1 = new ArrayList<>();

        al1.add("aa");
        al1.add("bb");
        al1.add("cc");
        List<String>al2 = new ArrayList<>();

        al2.add("cc");
        al2.add("dd");
        al2.add("aa");

        // retrieve all aa from both list and add in new list using java 8

    al2.addAll(al1);
        List<String>combinedList= al2.stream().filter(n->n.equals("aa")).collect(Collectors.toList());
        System.out.println(combinedList);

        /*HashSet<>
                SOLID:*/

        // class Interface:
        //

        //
        /*
        accountInvoices ->
        RailRoute( Terminals)-> map(IARS)
        TruckRoad -> drivers, products, productType

        /
        Report->Excel data -> downloadable format ->

        /download/excel

        @GetMapping
        rows-> cells ->Columns ->
        XssSheets
        Try IO


        Controller -> DownloadService -> Dao(TransactionsTable) -> getById(Name)

        proper

        @Entity

        find(
        findAll(








         */

    }
}
