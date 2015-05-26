package com.ivangusef.data.repository.datasource;

import android.support.annotation.NonNull;

import com.ivangusef.data.cache.LostNewsCache;
import com.ivangusef.data.entity.LostNewsEntity;
import com.ivangusef.data.net.Api;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public class RemoteLostNewsDataSource implements LostNewsDataSource {

    private final Api           api;
    private final LostNewsCache cache;

    private final Action1<List<LostNewsEntity>> saveToCache = new Action1<List<LostNewsEntity>>() {
        @Override
        public void call(@NonNull final List<LostNewsEntity> lostNewsEntities) {
            if (!lostNewsEntities.isEmpty()) {
                cache.put(lostNewsEntities);
            }
        }
    };

    public RemoteLostNewsDataSource(@NonNull final Api api, @NonNull final LostNewsCache cache) {
        this.api = api;
        this.cache = cache;
    }

    @Override
    public Observable<List<LostNewsEntity>> getLostNewsEntities() {
        return api.getLostNewsEntities().doOnNext(saveToCache);
    }
}
