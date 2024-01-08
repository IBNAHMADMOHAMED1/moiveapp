package com.api.movieapp.batch;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactory implements ThreadFactory {

    private final String threadNamePrefix;
    private final AtomicInteger threadCount = new AtomicInteger(0);

    public CustomThreadFactory(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }


    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(threadNamePrefix + "-" + threadCount.incrementAndGet());
        return thread;
    }
}
