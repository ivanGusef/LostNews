package com.ivangusef.data.executor;

import android.os.Process;
import android.support.annotation.NonNull;

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
    public void execute(@NonNull final Runnable runnable) {
        executorService.execute(runnable);
    }

    private static class JobThreadFactory implements ThreadFactory {

        private static final String THREAD_NAME = "android_";

        private int counter = 0;

        @NonNull
        @Override
        public Thread newThread(@NonNull final Runnable runnable) {
            final Thread t = new Thread(runnable, THREAD_NAME + counter++);
            t.setPriority(Process.THREAD_PRIORITY_BACKGROUND);
            return t;
        }
    }
}
