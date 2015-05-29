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
        for (LostNews lostNews : lostNewsList) {
            lostNewsModel = new LostNewsModel();
            lostNewsModel.setTitle(makeTitle(lostNews));
            lostNewsModel.setDescription(makeDescription(lostNews));
            lostNewsModel.setImageUrl(lostNews.getImageUrl());
            lostNewsModel.setSeasonEpisode(makeSeasonEpisode(lostNews));
            lostNewsModel.setPublishDate(makePublishDate(lostNews));
            lostNewsModel.setQualities(makeQualities(lostNews));
            lostNewsModel.setUrl(lostNews.getUrl());
            lostNewsModels.add(lostNewsModel);
        }

        return lostNewsModels;
    }

    private CharSequence makeTitle(@NonNull final LostNews lostNews) {
        return lostNews.getTitle().replaceAll("\\([a-zA-Z0-9].*?\\)", "").trim();
    }

    private CharSequence makeDescription(@NonNull final LostNews lostNews) {
        return lostNews.getDescription();
    }

    private CharSequence makeSeasonEpisode(@NonNull final LostNews lostNews) {
        String result = lostNews.getSeasonEpisode().replace("S", "").replace("E", ".");
        if (result.contains(".")) {
            return result;
        } else {
            return "";
        }
    }

    private CharSequence makePublishDate(@NonNull final LostNews lostNews) {
        return lostNews.getPublishDateTime().toString("d.MM HH:mm");
    }

    private List<CharSequence> makeQualities(@NonNull final LostNews lostNews) {
        final List<CharSequence> qualities = new ArrayList<>();
        for (String qualityStr : lostNews.getQualities()) {
            qualities.add(qualityStr);
        }
        return qualities;
    }
}
