package com.mlf.concurrency.example.concurrent;

import com.mlf.concurrency.annocations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.concurrent.*;


@Slf4j
@ThreadSafe
public class CopyOnWriteArraySetExample {
    private static Set<Integer> vector = new CopyOnWriteArraySet<>();

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
        log.info("vector size : {}", vector.size());
    }

    private static void update(int i) {
        vector.add(i);

    }
}
