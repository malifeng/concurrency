package com.mlf.concurrency.example.sync;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchroizedExample2 {

    // 修饰一个类
    public static void test1(int j){
        synchronized (SynchroizedExample2.class){
            for (int i = 0; i < 10; i++) {
                log.info("test1 {} - {}",j,i);
            }
        }
    }

    // 修饰一个静态方法
    public static synchronized void test2(int j){
        for (int i = 0; i < 10; i++) {
            log.info("test2 {} {}",j,i);
        }
    }


    public static void main(String[] args) {
        SynchroizedExample2 example1 = new SynchroizedExample2();
        SynchroizedExample2 example2 = new SynchroizedExample2();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(()->{
            example1.test1(1);
        });

        service.execute(()->{
            example2.test1(2);
        });
    }
}
