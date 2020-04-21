package com.mlf.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class CountDownLatchExample4 {

    private final static int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadName = i;
            service.execute(()->{
                try {
                    if(semaphore.tryAcquire()){ // 尝试获取一个许可，获取到就执行
                        test(threadName);
                        semaphore.release();
                    }
                } catch (InterruptedException e) {
                    log.error("exception",e);
                    e.printStackTrace();
                } finally {

                }
            });
        }

        service.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}",threadNum++);
        Thread.sleep(300);
    }
}
