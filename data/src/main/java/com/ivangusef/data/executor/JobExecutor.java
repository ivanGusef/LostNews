package com.ivangusef.data.executor;

import android.os.Process;

import com.ivangusef.domain.executor.ThreadExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Decorated {@link java.util.concurrent.ExecutorService}
 */
@Singleton
public class JobExecutor implements ThreadExecutor {

    private final ExecutorService executorService;

    @Inject
    public JobExecutor() {
        executorService = Executors.newCachedThreadPool(new JobThreadFactory());
    }

    @Override
    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("Runnable to execute cannot be null");
        }
        executorService.execute(runnable);
    }

    private static class JobThreadFactory implements ThreadFactory {

        private static final String THREAD_NAME = "android_";

        private int counter = 0;

        @Override
        public Thread newThread(Runnable runnable) {
            final Thread t = new Thread(runnable, THREAD_NAME + counter++);
            t.setPriority(Process.THREAD_PRIORITY_BACKGROUND);
            return t;
        }
    }
}
