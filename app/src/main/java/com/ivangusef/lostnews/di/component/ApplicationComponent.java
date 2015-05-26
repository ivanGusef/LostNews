package com.ivangusef.lostnews.di.component;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivangusef.domain.executor.PostExecutionThread;
import com.ivangusef.domain.executor.ThreadExecutor;
import com.ivangusef.domain.repository.LostNewsRepository;
import com.ivangusef.lostnews.di.module.ApplicationModule;
import com.ivangusef.lostnews.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(@NonNull final BaseActivity baseActivity);

    //Exposed by sub-graphs
    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    LostNewsRepository lostNewsRepository();
}
