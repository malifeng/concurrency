package com.mlf.concurrency.example.singleton;


import com.mlf.concurrency.annocations.ThreadSafe;

/**
 * 枚举模式：最安全
 */
@ThreadSafe
public class SingletonExample7 {

    private SingletonExample7() {

    }

    public static SingletonExample7 instance = null;


    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        // JVM保证这个方法只调用一次绝对的
        Singleton(){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance(){
            return singleton;
        }

    }
}
