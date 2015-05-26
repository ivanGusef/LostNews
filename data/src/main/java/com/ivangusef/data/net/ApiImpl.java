package com.ivangusef.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ivangusef.data.entity.LostNewsEntity;
import com.ivangusef.data.entity.mapper.ResponseDataMapper;
import com.ivangusef.data.exception.NetworkException;

import java.net.MalformedURLException;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public class ApiImpl implements Api {

    private final Context            context;
    private final ResponseDataMapper mapper;

    public ApiImpl(@NonNull final Context context, @NonNull final ResponseDataMapper mapper) {
        this.context = context;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<LostNewsEntity>> getLostNewsEntities() {
        return Observable.create(
                new Observable.OnSubscribe<List<LostNewsEntity>>() {
                    @Override
                    public void call(final Subscriber<? super List<LostNewsEntity>> subscriber) {
                        if (!isThereInternetConnection()) {
                            subscriber.onError(new NetworkException());
                        }

                        try {
                            final String response = getRssFromApi();
                            if (response != null) {
                                subscriber.onNext(mapper.transform(response));
                                subscriber.onCompleted();
                            } else {
                                subscriber.onError(new NetworkException("Response is null"));
                            }
                        } catch (Exception e) {
                            subscriber.onError(new NetworkException(e.getCause()));
                        }
                    }
                }
        );
    }

    @Nullable
    private String getRssFromApi() throws MalformedURLException {
        return ApiConnection.createGET(Api.API_URL_GET_RSS).requestSyncCall();
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }
}
