package com.mlf.concurrency.example.singleton;

import com.mlf.concurrency.annocations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

@ThreadSafe
@Slf4j
public class SingletonExample6 {

    private SingletonExample6() {

    }

    public static SingletonExample6 instance = null ;

    static {
        instance = new SingletonExample6();
    }





    public static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        log.info(String.valueOf(getInstance().hashCode()));
        log.info(String.valueOf(getInstance().hashCode()));
    }
}
