package com.ivangusef.lostnews.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ivangusef.lostnews.LostApplication;
import com.ivangusef.lostnews.di.component.ApplicationComponent;
import com.ivangusef.lostnews.di.module.ActivityModule;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(final int containerViewId, @NonNull final Fragment fragment, @Nullable final String tag) {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment, tag);
        fragmentTransaction.commit();
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link ApplicationComponent}
     */
    @NonNull
    protected ApplicationComponent getApplicationComponent() {
        return ((LostApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link ActivityModule}
     */
    @NonNull
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
