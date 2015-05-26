package com.ivangusef.data.net;

import com.ivangusef.data.entity.LostNewsEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public interface Api {
    String API_URL_GET_RSS = "http://www.lostfilm.tv/rssdd.xml";

    Observable<List<LostNewsEntity>> getLostNewsEntities();
}
