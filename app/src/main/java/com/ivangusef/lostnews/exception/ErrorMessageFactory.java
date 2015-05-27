package com.ivangusef.lostnews.exception;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ivangusef.data.exception.NetworkException;
import com.ivangusef.lostnews.R;

/**
 * Created by Ivan_Gusev1 on 5/27/2015.
 */
public final class ErrorMessageFactory {

    private static final String TAG = ErrorMessageFactory.class.getSimpleName();

    public static CharSequence create(@NonNull final Context context, @NonNull final Exception e) {
        Log.w(TAG, e.getMessage(), e);
        if (e instanceof NetworkException) {
            return context.getString(R.string.error_network);
        } else {
            return context.getString(R.string.error_common);
        }
    }

    private ErrorMessageFactory() {
    }
}
