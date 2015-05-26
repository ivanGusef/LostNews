package com.ivangusef.data.entity.mapper;

import android.support.annotation.NonNull;

import com.ivangusef.data.entity.LostNewsEntity;
import com.ivangusef.domain.LostNews;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
@Singleton
public class LostNewsDataMapper {

    private static final String TAG = LostNewsDataMapper.class.getSimpleName();

    @Inject
    public LostNewsDataMapper() {
    }

    @NonNull
    public List<LostNews> transform(@NonNull final List<LostNewsEntity> lostNewsEntities) {
        final List<LostNews> lostNewsList = new ArrayList<>();
        if (lostNewsEntities.isEmpty()) {
            return lostNewsList;
        }

        LostNews lostNews;
        for (LostNewsEntity lostNewsEntity : lostNewsEntities) {
            lostNews = new LostNews();
            lostNews.setTitle(lostNewsEntity.getTitle());
            lostNews.setDescription(lostNewsEntity.getDescription());
            lostNews.setImageUrl(lostNewsEntity.getImageUrl());
            lostNews.setSeasonEpisode(lostNewsEntity.getSeasonEpisode());
            lostNews.setPublishDateTime(lostNewsEntity.getPublishDateTime());
            lostNews.setQualities(lostNewsEntity.getQualities());
            lostNews.setUrl(lostNewsEntity.getUrl());
            lostNewsList.add(lostNews);
        }
        return lostNewsList;
    }

    @NonNull
    public List<LostNewsEntity> reverseTransform(@NonNull final List<LostNews> lostNewsList) {
        final List<LostNewsEntity> lostNewsEntities = new ArrayList<>();
        if (lostNewsList.isEmpty()) {
            return lostNewsEntities;
        }

        LostNewsEntity lostNewsEntity;
        for (LostNews lostNews : lostNewsList) {
            lostNewsEntity = new LostNewsEntity();
            lostNewsEntity.setTitle(lostNews.getTitle());
            lostNewsEntity.setDescription(lostNews.getDescription());
            lostNewsEntity.setImageUrl(lostNews.getImageUrl());
            lostNewsEntity.setSeasonEpisode(lostNews.getSeasonEpisode());
            lostNewsEntity.setPublishDateTime(lostNews.getPublishDateTime());
            lostNewsEntity.setQualities(lostNews.getQualities());
            lostNewsEntity.setUrl(lostNews.getUrl());
            lostNewsEntities.add(lostNewsEntity);
        }
        return lostNewsEntities;
    }
}
