package com.ivangusef.lostnews.di.module;

import android.support.annotation.NonNull;

import com.ivangusef.domain.LostNews;
import com.ivangusef.domain.interactor.GetNewsUseCase;
import com.ivangusef.domain.interactor.UseCase;
import com.ivangusef.lostnews.di.PerActivity;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
@Module
public class LostNewsModule {

    @NonNull
    @Provides
    @PerActivity
    @Named("news")
    UseCase<List<LostNews>> provideGetUserListUseCase(@NonNull final GetNewsUseCase getNewsUseCase) {
        return getNewsUseCase;
    }
}
