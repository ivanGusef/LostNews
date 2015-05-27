package com.ivangusef.data.entity;

import java.util.List;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public final class LostNewsEntity {
    private String       title;
    private String       description;
    private String       imageUrl;
    private String       seasonEpisode;
    private Long         publishDateTime;
    private List<String> qualities;
    private String       url;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSeasonEpisode() {
        return seasonEpisode;
    }

    public void setSeasonEpisode(final String seasonEpisode) {
        this.seasonEpisode = seasonEpisode;
    }

    public Long getPublishDateTime() {
        return publishDateTime;
    }

    public void setPublishDateTime(final Long publishDateTime) {
        this.publishDateTime = publishDateTime;
    }

    public List<String> getQualities() {
        return qualities;
    }

    public void setQualities(final List<String> qualities) {
        this.qualities = qualities;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final LostNewsEntity lostNewsEntity = (LostNewsEntity) o;

        return title.equals(lostNewsEntity.title) && seasonEpisode.equals(lostNewsEntity.seasonEpisode);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + seasonEpisode.hashCode();
        return result;
    }
}
