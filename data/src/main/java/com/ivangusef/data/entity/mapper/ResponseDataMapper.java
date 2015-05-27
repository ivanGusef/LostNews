package com.ivangusef.data.entity.mapper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ivangusef.data.entity.LostNewsEntity;
import com.ivangusef.data.entity.RssEntity;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public class ResponseDataMapper {

    private final ResponseRssMapper responseRssMapper = new ResponseRssMapper();

    @NonNull
    public List<LostNewsEntity> transform(@NonNull final String response) {
        final List<LostNewsEntity> lostNewsEntities = new ArrayList<>();

        final List<RssEntity> rssEntities = responseRssMapper.transform(response);
        LostNewsEntity current, previous = null;
        for (RssEntity rssEntity : rssEntities) {
            current = new LostNewsEntity();


            final String[] titleParts = rssEntity.getTitle().split("\\.");
            if (titleParts.length != 3) {
                continue;
            }
            current.setTitle(titleParts[0].trim());
            final String quality = seek(titleParts[1], "\\[(.*?)\\]");
            if (quality != null) {
                titleParts[1] = titleParts[1].replace(" [" + quality + "]", "");
                current.setQualities(new ArrayList<String>());
                current.getQualities().add(quality);
            }
            current.setDescription(titleParts[1].trim());
            current.setSeasonEpisode(titleParts[2].replaceAll("[()]", "").trim());

            String iconUrl = seek(rssEntity.getDescription(), "<img src=\"(.*?)\"\\s");
            if (iconUrl != null) {
                iconUrl = iconUrl.replaceAll("", "").trim();
                current.setImageUrl(iconUrl);
            }

            current.setPublishDateTime(DateTime.parse(rssEntity.getPubDate(), DateTimeFormat.forPattern("EE, dd MMM yyyy HH:mm:ss Z")).getMillis());
            current.setUrl(rssEntity.getLink());
            if (previous != null && previous.equals(current)) {
                if (current.getQualities() != null) {
                    previous.getQualities().addAll(current.getQualities());
                }
            } else {
                lostNewsEntities.add(current);
                previous = current;
            }
        }
        return lostNewsEntities;
    }

    @NonNull
    private List<String> seek(final String source, final String regexp, final int repeatCount) {
        final List<String> results = new ArrayList<>();
        final Pattern pattern = Pattern.compile(regexp);
        final Matcher matcher = pattern.matcher(source);

        int i = 0;
        while (i < repeatCount && matcher.find()) {
            results.add(matcher.group(1));
            i++;
        }

        return results;
    }

    @Nullable
    private String seek(final String source, final String regexp) {
        final List<String> results = seek(source, regexp, 1);
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }
}
