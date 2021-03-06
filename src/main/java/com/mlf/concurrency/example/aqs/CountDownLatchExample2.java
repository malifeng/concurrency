package com.mlf.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CountDownLatchExample2 {

    private final static int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadName = i;
            service.execute(()->{
                try {
                    semaphore.acquire();// 获取一个许可
                    test(threadName);
                } catch (InterruptedException e) {
                    log.error("exception",e);
                    e.printStackTrace();
                } finally {
                    semaphore.release(); // 释放一个许可
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
