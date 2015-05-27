package com.ivangusef.lostnews.model.mapper;

import android.support.annotation.NonNull;

import com.ivangusef.domain.LostNews;
import com.ivangusef.lostnews.di.PerActivity;
import com.ivangusef.lostnews.model.LostNewsModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
@PerActivity
public class LostNewsModelDataMapper {

    @Inject
    public LostNewsModelDataMapper() {
    }

    @NonNull
    public List<LostNewsModel> transform(@NonNull final List<LostNews> lostNewsList) {
        final List<LostNewsModel> lostNewsModels = new ArrayList<>();

        LostNewsModel lostNewsModel;
        List<CharSequence> qualities;
        for (LostNews lostNews : lostNewsList) {
            lostNewsModel = new LostNewsModel();
            lostNewsModel.setTitle(lostNews.getTitle());
            lostNewsModel.setDescription(lostNews.getDescription());
            lostNewsModel.setImageUrl(lostNews.getImageUrl());
            lostNewsModel.setSeasonEpisode(lostNews.getSeasonEpisode());
            lostNewsModel.setPublishDate(lostNews.getPublishDateTime().toString("d.MM HH:mm"));
            qualities = new ArrayList<>();
            for (String qualityStr : lostNews.getQualities()) {
                qualities.add(qualityStr);
            }
            lostNewsModel.setQualities(qualities);
            lostNewsModel.setUrl(lostNews.getUrl());
            lostNewsModels.add(lostNewsModel);
        }

        return lostNewsModels;
    }
}
