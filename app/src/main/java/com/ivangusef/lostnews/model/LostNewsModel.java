package com.ivangusef.lostnews.model;

import java.util.List;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public final class LostNewsModel {
    private CharSequence       title;
    private CharSequence       description;
    private String             imageUrl;
    private CharSequence       seasonEpisode;
    private CharSequence       publishDate;
    private List<CharSequence> qualities;
    private String             url;

    public CharSequence getTitle() {
        return title;
    }

    public void setTitle(final CharSequence title) {
        this.title = title;
    }

    public CharSequence getDescription() {
        return description;
    }

    public void setDescription(final CharSequence description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public CharSequence getSeasonEpisode() {
        return seasonEpisode;
    }

    public void setSeasonEpisode(final CharSequence seasonEpisode) {
        this.seasonEpisode = seasonEpisode;
    }

    public CharSequence getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(final CharSequence publishDate) {
        this.publishDate = publishDate;
    }

    public List<CharSequence> getQualities() {
        return qualities;
    }

    public void setQualities(final List<CharSequence> qualities) {
        this.qualities = qualities;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }
}
