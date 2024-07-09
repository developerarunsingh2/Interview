package com.ey;

public class SingleTon {

    private SingleTon(){}

    private static SingleTon instance;

    public static SingleTon getInstance(SingleTon instance)
    {
        if(instance == null)
        {
            instance = new SingleTon();
        }

        return instance;
    }

}
