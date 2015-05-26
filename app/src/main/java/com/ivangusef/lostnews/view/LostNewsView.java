package com.ivangusef.lostnews.view;

import android.support.annotation.NonNull;

import com.ivangusef.lostnews.model.LostNewsModel;

import java.util.List;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public interface LostNewsView extends LoadView {
    void renderLostNews(@NonNull final List<LostNewsModel> lostNewsModels);
}
