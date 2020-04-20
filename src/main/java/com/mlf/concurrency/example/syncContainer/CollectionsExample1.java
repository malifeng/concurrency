package com.mlf.concurrency.example.syncContainer;

import com.google.common.collect.Lists;
import com.mlf.concurrency.annocations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


@Slf4j
@ThreadSafe
public class CollectionsExample1 {
    private static List<Object> list = Collections.synchronizedList(Lists.newArrayList());

    // 请求总数
    public static int clientTotal = 5000;
    // 并发执行的总线程数
    public static int threadTotal = 200;


    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            int finalI = i;
            service.execute(() -> {
                try {
                    semaphore.acquire();
                    update(finalI);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        service.shutdown();
        log.info("list size : {}", list.size());
    }

    private static void update(int i) {
        list.add(i);

    }
}
