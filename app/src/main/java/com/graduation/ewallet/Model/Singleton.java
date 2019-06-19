package com.graduation.ewallet.Model;

public class Singleton {

    private static Singleton singletonInstance=null;

    public SingleTonModel userForm;

    private Singleton(){
        userForm =new SingleTonModel();
    }
    public static Singleton getInstance()
    {
        if (singletonInstance == null)
            singletonInstance = new Singleton();

        return singletonInstance;
    }

}
