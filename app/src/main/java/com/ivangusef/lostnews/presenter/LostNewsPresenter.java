package com.ivangusef.lostnews.presenter;

import android.support.annotation.NonNull;

import com.ivangusef.domain.LostNews;
import com.ivangusef.domain.exception.DefaultErrorBundle;
import com.ivangusef.domain.exception.ErrorBundle;
import com.ivangusef.domain.interactor.DefaultSubscriber;
import com.ivangusef.domain.interactor.UseCase;
import com.ivangusef.lostnews.di.PerActivity;
import com.ivangusef.lostnews.exception.ErrorMessageFactory;
import com.ivangusef.lostnews.model.LostNewsModel;
import com.ivangusef.lostnews.model.mapper.LostNewsModelDataMapper;
import com.ivangusef.lostnews.view.LostNewsView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
@PerActivity
public final class LostNewsPresenter extends DefaultSubscriber<List<LostNews>> implements Presenter {

    private LostNewsView lostNewsView;

    private final UseCase<List<LostNews>> getLostNewsUseCase;
    private final LostNewsModelDataMapper lostNewsModelDataMapper;

    @Inject
    public LostNewsPresenter(@NonNull @Named("news") final UseCase<List<LostNews>> getLostNewsUseCase,
                             @NonNull final LostNewsModelDataMapper lostNewsModelDataMapper) {
        this.getLostNewsUseCase = getLostNewsUseCase;
        this.lostNewsModelDataMapper = lostNewsModelDataMapper;
    }

    public void setView(@NonNull final LostNewsView view) {
        lostNewsView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        getLostNewsUseCase.unsubscribe();
    }

    /**
     * Initializes the presenter by start retrieving the news.
     */
    public void initialize() {
        loadNews();
    }

    private void loadNews() {
        showViewLoading();
        getNews();
    }

    private void showViewLoading() {
        lostNewsView.showLoading();
    }

    private void hideViewLoading() {
        lostNewsView.hideLoading();
    }

    private void showErrorMessage(@NonNull final ErrorBundle errorBundle) {
        final CharSequence errorMessage = ErrorMessageFactory.create(
                lostNewsView.getContext(),
                errorBundle.getException()
        );
        lostNewsView.showError(errorMessage);
    }

    private void showLostNewsInView(@NonNull final List<LostNews> lostNewsList) {
        lostNewsView.renderLostNews(lostNewsModelDataMapper.transform(lostNewsList));
    }

    public void onUserClick(@NonNull final LostNewsModel lostNewsModel) {
        lostNewsView.viewLostNews(lostNewsModel);
    }

    private void getNews() {
        getLostNewsUseCase.execute(this);
    }

    @Override
    public void onNext(@NonNull final List<LostNews> lostNewsList) {
        showLostNewsInView(lostNewsList);
    }

    @Override
    public void onCompleted() {
        hideViewLoading();
    }

    @Override
    public void onError(Throwable e) {
        hideViewLoading();
        showErrorMessage(new DefaultErrorBundle((Exception) e));
    }
}
