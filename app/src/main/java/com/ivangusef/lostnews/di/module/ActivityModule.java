package com.ivangusef.lostnews.di.module;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.ivangusef.lostnews.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(@NonNull final Activity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @NonNull
    @Provides
    @PerActivity
    Activity activity() {
        return activity;
    }
}
