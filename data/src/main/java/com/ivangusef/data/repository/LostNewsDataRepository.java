package com.ivangusef.data.repository;

import android.support.annotation.NonNull;

import com.ivangusef.data.entity.LostNewsEntity;
import com.ivangusef.data.entity.mapper.LostNewsDataMapper;
import com.ivangusef.data.repository.datasource.LostNewsDataSourceProvider;
import com.ivangusef.domain.LostNews;
import com.ivangusef.domain.repository.LostNewsRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
@Singleton
public class LostNewsDataRepository implements LostNewsRepository {

    private final LostNewsDataSourceProvider dataSourceProvider;
    private final LostNewsDataMapper         mapper;

    private final Func1<List<LostNewsEntity>, List<LostNews>> mapperFunction = new Func1<List<LostNewsEntity>, List<LostNews>>() {
        @Override
        public List<LostNews> call(final List<LostNewsEntity> lostNewsEntities) {
            return mapper.transform(lostNewsEntities);
        }
    };

    @Inject
    public LostNewsDataRepository(@NonNull final LostNewsDataSourceProvider dataSourceProvider, @NonNull final LostNewsDataMapper mapper) {
        this.dataSourceProvider = dataSourceProvider;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<LostNews>> getNews() {
        return dataSourceProvider.getDataSource().getLostNewsEntities().map(mapperFunction);
    }
}
