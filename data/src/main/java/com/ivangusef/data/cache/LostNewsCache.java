package com.ivangusef.data.cache;

import android.support.annotation.NonNull;

import com.ivangusef.data.entity.LostNewsEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public interface LostNewsCache {

    void put(@NonNull final List<LostNewsEntity> LostNewsEntities);

    @NonNull
    Observable<List<LostNewsEntity>> get();

    boolean isExpired();

    void evict();
}
