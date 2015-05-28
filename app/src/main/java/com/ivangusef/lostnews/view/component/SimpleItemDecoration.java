package com.ivangusef.lostnews.view.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by Иван on 28.05.2015.
 */
public final class SimpleItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable divider;

    public SimpleItemDecoration(@NonNull final Context context, @DrawableRes final int dividerResId) {
        this.divider = context.getResources().getDrawable(dividerResId);
    }

    @Override
    public void onDrawOver(@NonNull final Canvas c, @NonNull final RecyclerView parent, @NonNull final RecyclerView.State state) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount() - 1;
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + divider.getIntrinsicHeight();

            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }
}
