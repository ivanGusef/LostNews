package com.ivangusef.data.cache.serializer;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ivangusef.data.entity.LostNewsEntity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Serializer for rss feed of LostFilm.TV news
 */
@Singleton
public class LostNewsSerializer {

    private static final String TAG = LostNewsSerializer.class.getSimpleName();

    private final Gson gson = new Gson();

    @Inject
    public LostNewsSerializer() {
    }

    @NonNull
    public String serialize(@NonNull final List<LostNewsEntity> lostNewsEntities) {
        return gson.toJson(lostNewsEntities);
    }

    @Nullable
    public List<LostNewsEntity> deserialize(@NonNull final String json) {
        return gson.fromJson(json, new TypeToken<List<LostNewsEntity>>() {}.getType());
    }
}
