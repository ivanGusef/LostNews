package com.ivangusef.lostnews.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Window;

import com.ivangusef.lostnews.R;
import com.ivangusef.lostnews.di.HasComponent;
import com.ivangusef.lostnews.di.component.DaggerLostNewsComponent;
import com.ivangusef.lostnews.di.component.LostNewsComponent;
import com.ivangusef.lostnews.model.LostNewsModel;
import com.ivangusef.lostnews.view.fragment.LostNewsFragment;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public final class LostNewsActivity extends BaseActivity implements HasComponent<LostNewsComponent>, LostNewsFragment.OnLostNewsClickListener {

    private static final String FRAGMENT_CONTENT = "contentFragment";

    private LostNewsComponent lostNewsComponent;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_news);
        if (savedInstanceState == null) {
            addFragment(R.id.content, LostNewsFragment.create(), FRAGMENT_CONTENT);
        }

        initializeInjector();
    }

    private void initializeInjector() {
        lostNewsComponent = DaggerLostNewsComponent.builder()
                                                   .applicationComponent(getApplicationComponent())
                                                   .activityModule(getActivityModule())
                                                   .build();
    }

    @Override
    public LostNewsComponent getComponent() {
        return lostNewsComponent;
    }

    @Override
    public void onLostNewsClick(@NonNull final LostNewsModel lostNewsModel) {
        //Open browser and go to news link
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(lostNewsModel.getUrl())));
    }
}
