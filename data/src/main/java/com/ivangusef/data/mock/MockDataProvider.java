package com.ivangusef.data.mock;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RawRes;

import com.ivangusef.data.R;
import com.ivangusef.data.entity.LostNewsEntity;
import com.ivangusef.data.entity.mapper.ResponseDataMapper;
import com.ivangusef.data.exception.NetworkException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Ivan_Gusev1 on 5/29/2015.
 */
public class MockDataProvider {

    private final Context            context;
    private final ResponseDataMapper mapper;

    public MockDataProvider(@NonNull final Context context, @NonNull final ResponseDataMapper mapper) {
        this.context = context;
        this.mapper = mapper;
    }

    public Observable<List<LostNewsEntity>> getLostNewsEntities() {
        return Observable.create(
                new Observable.OnSubscribe<List<LostNewsEntity>>() {
                    @Override
                    public void call(final Subscriber<? super List<LostNewsEntity>> subscriber) {
                        try {
                            subscriber.onNext(mapper.transform(readRawResource(R.raw.mock_rss)));
                            subscriber.onCompleted();
                        } catch (Exception e) {
                            subscriber.onError(new NetworkException(e.getCause()));
                        }
                    }
                }
        );
    }

    private String readRawResource(@RawRes final int rawResId) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(rawResId), Charset.forName("windows-1251")));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    //ignored
                }
            }
        }
    }
}
