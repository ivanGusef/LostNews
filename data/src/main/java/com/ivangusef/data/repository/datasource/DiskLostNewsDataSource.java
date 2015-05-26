package com.ivangusef.data.repository.datasource;

import android.support.annotation.NonNull;

import com.ivangusef.data.cache.LostNewsCache;
import com.ivangusef.data.entity.LostNewsEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public class DiskLostNewsDataSource implements LostNewsDataSource {

    private final LostNewsCache cache;

    public DiskLostNewsDataSource(@NonNull final LostNewsCache cache) {
        this.cache = cache;
    }

    @Override
    public Observable<List<LostNewsEntity>> getLostNewsEntities() {
        return cache.get();
    }
}
