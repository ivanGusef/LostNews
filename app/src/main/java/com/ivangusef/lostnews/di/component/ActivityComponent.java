package com.ivangusef.lostnews.di.component;

import android.app.Activity;

import com.ivangusef.lostnews.di.PerActivity;
import com.ivangusef.lostnews.di.module.ActivityModule;
import com.ivangusef.lostnews.di.module.ApplicationModule;

import dagger.Component;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
@PerActivity
@Component(dependencies = ApplicationModule.class, modules = ActivityModule.class)
public interface ActivityComponent {
    //Exposed by sub-graphs
    Activity activity();
}
