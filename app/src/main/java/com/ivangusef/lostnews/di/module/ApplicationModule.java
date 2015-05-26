package com.ivangusef.lostnews.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivangusef.data.cache.LostNewsCache;
import com.ivangusef.data.cache.LostNewsCacheImpl;
import com.ivangusef.data.executor.JobExecutor;
import com.ivangusef.data.repository.LostNewsDataRepository;
import com.ivangusef.domain.executor.PostExecutionThread;
import com.ivangusef.domain.executor.ThreadExecutor;
import com.ivangusef.domain.repository.LostNewsRepository;
import com.ivangusef.lostnews.LostApplication;
import com.ivangusef.lostnews.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
@Module
public class ApplicationModule {

    private final LostApplication application;

    public ApplicationModule(@NonNull final LostApplication application) {
        this.application = application;
    }

    @NonNull
    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @NonNull
    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(@NonNull final JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @NonNull
    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(@NonNull final UIThread uiThread) {
        return uiThread;
    }

    @NonNull
    @Provides
    @Singleton
    LostNewsCache provideUserCache(@NonNull final LostNewsCacheImpl lostNewsCache) {
        return lostNewsCache;
    }

    @NonNull
    @Provides
    @Singleton
    LostNewsRepository provideUserRepository(@NonNull final LostNewsDataRepository lostNewsDataRepository) {
        return lostNewsDataRepository;
    }
}
