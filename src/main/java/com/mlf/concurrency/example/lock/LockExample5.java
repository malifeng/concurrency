package com.mlf.concurrency.example.lock;

import com.mlf.concurrency.annocations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import java.util.concurrent.locks.StampedLock;

@Slf4j
@ThreadSafe
public class LockExample5 {
    // 请求总数
     public static int clientTotal = 5000;
     // 并发执行的总线程数
     public static int threadTotal = 200;

     public static int count = 0;

     private final static StampedLock lock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            service.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        service.shutdown();
        log.info("count:{}",count);

    }

    private static void add(){
        long stamp = lock.writeLock();
        try {
            count++;
        }finally {
            lock.unlock(stamp);
        }
    }
}