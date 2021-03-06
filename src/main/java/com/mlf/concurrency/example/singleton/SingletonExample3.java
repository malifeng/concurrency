package com.mlf.concurrency.example.singleton;

import com.mlf.concurrency.annocations.ThreadSafe;

@ThreadSafe
public class SingletonExample3 {

    private SingletonExample3() {

    }

    public static SingletonExample3 instance = null;


    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
