package com.mlf.concurrency.example.singleton;

import com.mlf.concurrency.annocations.NotThreadSafe;

@NotThreadSafe
public class SingletonExample4 {

    private SingletonExample4() {

    }

    public static SingletonExample4 instance = null;


    public static SingletonExample4 getInstance() {
        if (instance == null) {
            synchronized (SingletonExample4.class){
                instance = new SingletonExample4();
            }
        }
        return instance;
    }
}
