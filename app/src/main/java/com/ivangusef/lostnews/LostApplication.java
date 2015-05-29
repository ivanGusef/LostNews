package com.ivangusef.lostnews;

import android.app.Application;

import com.ivangusef.lostnews.di.HasComponent;
import com.ivangusef.lostnews.di.component.ApplicationComponent;
import com.ivangusef.lostnews.di.component.DaggerApplicationComponent;
import com.ivangusef.lostnews.di.module.ApplicationModule;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public class LostApplication extends Application implements HasComponent<ApplicationComponent> {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    @Override
    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
