package com.ivangusef.lostnews.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivangusef.lostnews.R;
import com.ivangusef.lostnews.di.component.LostNewsComponent;
import com.ivangusef.lostnews.model.LostNewsModel;
import com.ivangusef.lostnews.presenter.LostNewsPresenter;
import com.ivangusef.lostnews.view.LostNewsView;
import com.ivangusef.lostnews.view.adapter.LostNewsAdapter;
import com.ivangusef.lostnews.view.component.SimpleItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public final class LostNewsFragment extends BaseFragment implements LostNewsView {

    public static LostNewsFragment create() {
        return new LostNewsFragment();
    }

    public interface OnLostNewsClickListener {
        void onLostNewsClick(@NonNull final LostNewsModel lostNewsModel);
    }

    @Inject
    LostNewsPresenter lostNewsPresenter;

    @InjectView(R.id.recycler)
    RecyclerView recyclerView;
    @InjectView(R.id.progress)
    View         progressView;
    @InjectView(R.id.empty)
    View         emptyView;

    private LostNewsAdapter         adapter;
    private OnLostNewsClickListener listener;

    private final LostNewsAdapter.OnItemClickListener onItemClickListener = new LostNewsAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(@NonNull final LostNewsModel lostNewsModel) {
            if (lostNewsPresenter != null) {
                lostNewsPresenter.onUserClick(lostNewsModel);
            }
        }
    };

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnLostNewsClickListener) {
            listener = (OnLostNewsClickListener) activity;
        }
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_lost_news, container, false);
        ButterKnife.inject(this, fragmentView);
        setupUI();

        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
        loadLostNews();
    }

    @Override
    public void onResume() {
        super.onResume();
        lostNewsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        lostNewsPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lostNewsPresenter.destroy();
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    private void initialize() {
        getComponent(LostNewsComponent.class).inject(this);
        lostNewsPresenter.setView(this);
    }

    private void setupUI() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new SimpleItemDecoration(getActivity(), android.R.drawable.divider_horizontal_bright));
        adapter = new LostNewsAdapter(getActivity(), onItemClickListener);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Loads all news.
     */
    private void loadLostNews() {
        lostNewsPresenter.initialize();
    }

    @Override
    public void renderLostNews(@NonNull final List<LostNewsModel> lostNewsModels) {
        adapter.setData(lostNewsModels);
    }

    @Override
    public void viewLostNews(@NonNull final LostNewsModel lostNewsModel) {
        if (listener != null) {
            listener.onLostNewsClick(lostNewsModel);
        }
    }

    @Override
    public void showLoading() {
        getActivity().setProgressBarIndeterminateVisibility(true);
        progressView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        getActivity().setProgressBarIndeterminateVisibility(false);
        progressView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(@NonNull final CharSequence message) {
        showToastMessage(message);
    }

    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }
}
