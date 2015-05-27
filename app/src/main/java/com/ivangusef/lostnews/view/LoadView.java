package com.ivangusef.lostnews.view;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
interface LoadView {
    void showLoading();

    void hideLoading();

    void showError(@NonNull final CharSequence message);

    Context getContext();
}
