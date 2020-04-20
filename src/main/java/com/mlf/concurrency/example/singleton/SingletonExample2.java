package com.mlf.concurrency.example.singleton;

import com.mlf.concurrency.annocations.ThreadSafe;

@ThreadSafe
public class SingletonExample2 {

    private SingletonExample2() {

    }

    public static SingletonExample2 instance = new SingletonExample2() ;


    public static SingletonExample2 getInstance() {
        if (instance == null) {
            instance = new SingletonExample2();
        }
        return instance;
    }
}
