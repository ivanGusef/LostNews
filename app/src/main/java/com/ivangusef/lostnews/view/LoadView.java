package com.ivangusef.lostnews.view;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
interface LoadView {
    void showLoading();

    void hideLoading();

    void showEmpty();

    void hideEmpty();

    void showContent();

    void hideContent();

    void showError(@NonNull final String message);

    Context getContext();
}
