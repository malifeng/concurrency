package com.mlf.concurrency.example.sync;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchroizedExample1 {

    public  void test1(int j){
        synchronized (this){
            for (int i = 0; i < 10; i++) {
                log.info("test1 {} - {}",j,i);
            }
        }
    }

    public synchronized void test2(int j){
        for (int i = 0; i < 10; i++) {
            log.info("test2 {} - {}",j,i);
        }
    }

    public static void main(String[] args) {
        SynchroizedExample1 example1 = new SynchroizedExample1();
        SynchroizedExample1 example2 = new SynchroizedExample1();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(()->{
            example1.test2(1);
        });

        service.execute(()->{
            example2.test2(2);
        });
    }
}
