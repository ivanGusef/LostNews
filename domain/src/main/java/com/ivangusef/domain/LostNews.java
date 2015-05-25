package com.ivangusef.domain;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Class that represents LostNews in the domain layer.
 */
public final class LostNews {

    private String       title;
    private String       description;
    private String       imageUrl;
    /**
     * e.g. 01.05 means 1st season, 5th episode or 01 means completed 1st season
     */
    private String       seasonEpisode;
    private DateTime     publishDateTime;
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

    public DateTime getPublishDateTime() {
        return publishDateTime;
    }

    public void setPublishDateTime(final DateTime publishDateTime) {
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

        final LostNews lostNews = (LostNews) o;

        return title.equals(lostNews.title) && seasonEpisode.equals(lostNews.seasonEpisode);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + seasonEpisode.hashCode();
        return result;
    }
}
