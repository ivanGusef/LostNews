package com.ivangusef.domain.repository;

import com.ivangusef.domain.LostNews;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link LostNews} related data.
 */
public interface LostNewsRepository {
    /**
     * Get an {@link Observable} which will emit a {@link List} of {@link LostNews}.
     */
    Observable<List<LostNews>> getNews();
}
