package com.ivangusef.lostnews.di.component;

import android.support.annotation.NonNull;

import com.ivangusef.lostnews.di.PerActivity;
import com.ivangusef.lostnews.di.module.ActivityModule;
import com.ivangusef.lostnews.di.module.LostNewsModule;
import com.ivangusef.lostnews.view.fragment.LostNewsFragment;

import dagger.Component;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, LostNewsModule.class})
public interface LostNewsComponent extends ActivityComponent {
    void inject(@NonNull final LostNewsFragment fragment);
}
