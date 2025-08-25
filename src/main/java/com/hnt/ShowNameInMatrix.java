package com.hnt;

import java.util.Scanner;

public class ShowNameInMatrix {

    public static void main(String[] args) {
        System.out.println("Put your name");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine().replaceAll(" ","*"); //taking input from user and replacing spaces with *

        int nameIndex = 0;
        System.out.println("Please enter the required number of columns");
        int inputColumns = sc.nextInt(); // taking input for number of columns

        int userNameLength = userName.length();

        while(nameIndex<userNameLength)
        {
            for(int index=0;index<inputColumns;index++){
                if(nameIndex<userNameLength){
                    System.out.print(userName.charAt(nameIndex)+" "); // printing character and adding space between alphabets
                    nameIndex++;
                }

                else{
                    System.out.print("* "); // replacing blank spaces in matrix with * in last row
                }
            }

            System.out.println();
        }

        sc.close();
    }
}
