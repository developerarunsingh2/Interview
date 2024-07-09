package com.ey;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicate {
    public static void main(String[] args) {
      /*  Input: nums [1,1,2]
        Output: nums = [1,2,_]
        2. Input: nums = [0,0,1,1,1,2,2,3,3,4]
        Output: nums = [0,1,2,3,4,_,_,_,_,_]
*/


        /*


         */

       // nums [1,1,2]
        int nums[] = {0,0,1,1,1,2,2,3,3,4};
        Set<Integer> set = new LinkedHashSet<>();

        for(int i: nums)
        {
            set.add(i);
        }



        int setSize = set.size();

        int j=0;
        for(int i: set)
        {
            nums[j]=i;
                    j++;
        }

        for(int k = setSize;k<nums.length;k++)
        {
            nums[k]= -1;
        }

        ////print array

        for(int i=0;i<nums.length;i++)
        {
            System.out.println(nums[i]);
        }

        System.out.println(new A().aMethod().bMethod().cMethod("output"));

        }


    static class A{
        public B aMethod(){
           return new B();
        }

        class B{
            public C bMethod()
            {
               return new C();
            }
        }
        class C{


            public String cMethod(String str){

                return str;
            }
        }

//        //
//        new A()
//                .aMethod()
//               C .bMethod()
//                .cMethod()








    }
}
