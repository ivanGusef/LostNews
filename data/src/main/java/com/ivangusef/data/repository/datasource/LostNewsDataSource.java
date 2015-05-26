package com.ivangusef.data.repository.datasource;

import com.ivangusef.data.entity.LostNewsEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public interface LostNewsDataSource {
    Observable<List<LostNewsEntity>> getLostNewsEntities();
}
