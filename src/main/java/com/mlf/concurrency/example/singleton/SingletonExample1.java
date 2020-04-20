package com.mlf.concurrency.example.singleton;

import com.mlf.concurrency.annocations.NotThreadSafe;

@NotThreadSafe
public class SingletonExample1 {

    private SingletonExample1() {

    }

    public static SingletonExample1 instance = null;


    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
