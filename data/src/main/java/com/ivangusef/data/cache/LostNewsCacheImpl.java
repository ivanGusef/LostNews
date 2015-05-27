package com.ivangusef.data.cache;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivangusef.data.cache.serializer.LostNewsSerializer;
import com.ivangusef.data.entity.LostNewsEntity;
import com.ivangusef.data.exception.CacheNotFoundException;
import com.ivangusef.domain.executor.ThreadExecutor;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;

/**
 * {LostNewsCache} implementation
 */
@Singleton
public class LostNewsCacheImpl implements LostNewsCache {

    private static final String TAG = LostNewsCacheImpl.class.getSimpleName();

    private static final String CACHE_DIR_NAME  = "data_cache";
    private static final String CACHE_FILE_NAME = "news_cache";

    private static final String SETTINGS_FILE_NAME             = "com.ivangusef.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final long EXPIRATION_TIME = 600000; //10 minutes

    private final Context            context;
    private final FileManager        fileManager;
    private final LostNewsSerializer serializer;
    private final ThreadExecutor     threadExecutor;
    private final File               cacheDir;

    @Inject
    public LostNewsCacheImpl(@NonNull final Context context, @NonNull final FileManager fileManager, @NonNull final LostNewsSerializer serializer,
                             @NonNull final ThreadExecutor threadExecutor) {
        this.context = context;
        this.fileManager = fileManager;
        this.serializer = serializer;
        this.threadExecutor = threadExecutor;

        final File cacheDir = new File(context.getCacheDir(), CACHE_DIR_NAME);
        if (cacheDir.exists() || cacheDir.mkdirs()) {
            this.cacheDir = cacheDir;
        } else {
            this.cacheDir = context.getCacheDir();
        }
    }

    @Override
    public synchronized void put(@NonNull final List<LostNewsEntity> lostNewsEntities) {
        executeAsynchronously(new CacheWriter(fileManager, getCacheFile(), serializer.serialize(lostNewsEntities)));
        setLastCacheUpdateTimeMillis();
    }

    @NonNull
    @Override
    public synchronized Observable<List<LostNewsEntity>> get() {
        return Observable.create(
                new Observable.OnSubscribe<List<LostNewsEntity>>() {
                    @Override
                    public void call(final Subscriber<? super List<LostNewsEntity>> subscriber) {
                        final String fileContent = fileManager.readFromFile(getCacheFile());
                        final List<LostNewsEntity> lostNewsEntities = serializer.deserialize(fileContent);
                        if (lostNewsEntities != null) {
                            subscriber.onNext(lostNewsEntities);
                            subscriber.onCompleted();
                        } else {
                            subscriber.onError(new CacheNotFoundException());
                        }
                    }
                }
        );
    }

    @Override
    public boolean isExpired() {
        final long currentTime = System.currentTimeMillis();
        final long lastUpdateTime = getLastCacheUpdateTimeMillis();

        final boolean expired = (currentTime - lastUpdateTime) > EXPIRATION_TIME;

        if (expired) {
            evict();
        }

        return expired;
    }

    @Override
    public void evict() {
        executeAsynchronously(new CacheEvictor(fileManager, cacheDir));
    }

    @NonNull
    private File getCacheFile() {
        return new File(cacheDir, CACHE_FILE_NAME);
    }

    /**
     * Set in millis, the last time the cache was accessed.
     */
    private void setLastCacheUpdateTimeMillis() {
        fileManager.writeToPreferences(context, SETTINGS_FILE_NAME, SETTINGS_KEY_LAST_CACHE_UPDATE, System.currentTimeMillis());
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private long getLastCacheUpdateTimeMillis() {
        return fileManager.getFromPreferences(context, SETTINGS_FILE_NAME, SETTINGS_KEY_LAST_CACHE_UPDATE);
    }

    /**
     * Executes a {@link Runnable} in another Thread.
     *
     * @param runnable {@link Runnable} to execute
     */
    private void executeAsynchronously(Runnable runnable) {
        threadExecutor.execute(runnable);
    }

    /**
     * {@link Runnable} class for writing to disk.
     */
    private static class CacheWriter implements Runnable {
        private final FileManager fileManager;
        private final File        fileToWrite;
        private final String      fileContent;

        CacheWriter(@NonNull final FileManager fileManager, @NonNull final File fileToWrite, @NonNull final String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            fileManager.writeToFile(fileToWrite, fileContent);
        }
    }

    /**
     * {@link Runnable} class for evicting all the cached files
     */
    private static class CacheEvictor implements Runnable {
        private final FileManager fileManager;
        private final File        cacheDir;

        CacheEvictor(@NonNull final FileManager fileManager, @NonNull final File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }

        @Override
        public void run() {
            fileManager.clearDirectory(cacheDir);
        }
    }
}
