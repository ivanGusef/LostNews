package com.ivangusef.domain.interactor;

import com.ivangusef.domain.LostNews;
import com.ivangusef.domain.executor.PostExecutionThread;
import com.ivangusef.domain.executor.ThreadExecutor;
import com.ivangusef.domain.repository.LostNewsRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public class GetNewsUseCase extends UseCase<List<LostNews>> {

    private final LostNewsRepository repository;

    @Inject
    public GetNewsUseCase(final ThreadExecutor threadExecutor, final PostExecutionThread postExecutionThread, final LostNewsRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    public Observable<List<LostNews>> buildUseCaseObservable() {
        return repository.getNews();
    }
}
