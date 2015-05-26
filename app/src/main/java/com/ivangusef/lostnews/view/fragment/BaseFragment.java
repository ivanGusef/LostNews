package com.ivangusef.lostnews.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.ivangusef.lostnews.di.HasComponent;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(@NonNull final String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @NonNull
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(@NonNull final Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
