package com.ey;

public class CharacterOccurance {
    public static void main(String[] args) {

        String s ="aaaabbbcceeeefff";
       // a4b3c2d2
        String output = "";
        int counter = 1;
        char c = '!';

        for(int i=0;i<s.length()-1;i++)
        {


            if(s.charAt(i)==s.charAt(i+1))
            {
                c=s.charAt(i);
                counter++;
                continue;
            }

            else {
             //   counter = 1;
//                c = s.charAt(i);
            }

            output = output+c+counter;
            counter = 1;
        }

        System.out.println(output);


    }

//    Employee: id, name, salary
//
//    select salary from Employee order by salary desc limit 5
//    minus
//    select salary from Employee order by salary desc limit 4
//
//    select salary from Employee order by salary desc skip 4;


}
