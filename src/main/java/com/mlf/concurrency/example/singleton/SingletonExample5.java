package com.mlf.concurrency.example.singleton;

import com.mlf.concurrency.annocations.ThreadSafe;

@ThreadSafe
public class SingletonExample5 {

    private SingletonExample5() {

    }

    public volatile static SingletonExample5 instance = null;


    public static SingletonExample5 getInstance() {
        if (instance == null) {
            synchronized (SingletonExample5.class){
                instance = new SingletonExample5();
            }
        }
        return instance;
    }
}
