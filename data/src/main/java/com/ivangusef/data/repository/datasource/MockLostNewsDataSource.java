package com.ivangusef.data.repository.datasource;

import android.support.annotation.NonNull;

import com.ivangusef.data.entity.LostNewsEntity;
import com.ivangusef.data.mock.MockDataProvider;

import java.util.List;

import rx.Observable;

/**
 * Created by Ivan_Gusev1 on 5/29/2015.
 */
public class MockLostNewsDataSource implements LostNewsDataSource {

    private final MockDataProvider mockDataProvider;

    public MockLostNewsDataSource(@NonNull final MockDataProvider mockDataProvider) {
        this.mockDataProvider = mockDataProvider;
    }

    @Override
    public Observable<List<LostNewsEntity>> getLostNewsEntities() {
        return mockDataProvider.getLostNewsEntities();
    }
}
