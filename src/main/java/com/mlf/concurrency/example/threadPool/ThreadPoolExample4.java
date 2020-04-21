package com.mlf.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;


@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {
        ScheduledExecutorService service =  Executors.newScheduledThreadPool(5);

//        service.schedule(new Runnable() {
//            @Override
//            public void run() {
//                log.warn("schedule run");
//            }
//        },3, TimeUnit.SECONDS);

        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.warn("schedule run");
            }
        },1,3,TimeUnit.SECONDS);
        service.shutdown();
    }
}
