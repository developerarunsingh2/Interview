package com.ey;

public class Singlet {

    private  Singlet(){}
    public static  Singlet instance;
    public static Singlet getInstance()
    {

        // below if condition helps to
        if(instance!=null)
        {
            throw new RuntimeException("Do not try to break the Singleton class");
        }

        if(instance==null)
          synchronized(instance){

        {
            instance = new Singlet();
        }
       };

        return instance;
    }
}
