package com.ivangusef.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivangusef.data.cache.LostNewsCache;
import com.ivangusef.data.entity.mapper.ResponseDataMapper;
import com.ivangusef.data.net.Api;
import com.ivangusef.data.net.ApiImpl;

import java.lang.ref.WeakReference;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
@Singleton
public final class LostNewsDataSourceProvider {

    private final Context       context;
    private final LostNewsCache cache;

    private WeakReference<LostNewsDataSource> remoteDataSource;
    private WeakReference<LostNewsDataSource> localDataSource;

    @Inject
    public LostNewsDataSourceProvider(@NonNull final Context context, @NonNull final LostNewsCache cache) {
        this.context = context;
        this.cache = cache;
    }

    @NonNull
    public LostNewsDataSource getDataSource() {
        if (cache.isExpired()) {
            return getRemoteDataSource();
        } else {
            return getDiskDataSource();
        }
    }

    @NonNull
    private LostNewsDataSource getRemoteDataSource() {
        if (remoteDataSource == null || remoteDataSource.get() == null) {
            final Api api = new ApiImpl(context, new ResponseDataMapper());
            remoteDataSource = new WeakReference<LostNewsDataSource>(new RemoteLostNewsDataSource(api, cache));
        }

        return remoteDataSource.get();
    }

    @NonNull
    private LostNewsDataSource getDiskDataSource() {
        if (localDataSource == null || localDataSource.get() == null) {
            localDataSource = new WeakReference<LostNewsDataSource>(new DiskLostNewsDataSource(cache));
        }

        return localDataSource.get();
    }
}
