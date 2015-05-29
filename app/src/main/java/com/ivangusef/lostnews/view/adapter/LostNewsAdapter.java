package com.ivangusef.lostnews.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ivangusef.lostnews.R;
import com.ivangusef.lostnews.model.LostNewsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public final class LostNewsAdapter extends RecyclerView.Adapter<LostNewsViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(@NonNull final LostNewsModel lostNewsModel);
    }

    private final Context             context;
    private final List<LostNewsModel> data;

    private final int                       qualityColor;
    private final LinearLayout.LayoutParams qualityLayoutParams;

    private final OnItemClickListener onItemClickListener;

    public LostNewsAdapter(@NonNull final Context context, final OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        data = new ArrayList<>();

        qualityColor = context.getResources().getColor(R.color.primary_material_light);
        qualityLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        qualityLayoutParams.setMargins(0, 0, (int) (context.getResources().getDisplayMetrics().density * 8), 0);
    }

    @Override
    public LostNewsViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(context).inflate(R.layout.item_lost_news, parent, false);
        return new LostNewsViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(final LostNewsViewHolder holder, final int position) {
        final LostNewsModel model = data.get(position);
        holder.setLostNewsModel(model);
        holder.titleView.setText(model.getTitle());
        holder.descriptionView.setText(model.getDescription());
        holder.seasonEpisodeView.setText(model.getSeasonEpisode());
        holder.pubDateView.setText(model.getPublishDate());
        holder.qualitiesContentView.removeAllViews();
        for (CharSequence quality : model.getQualities()) {
            final TextView qualityView = new TextView(context);
            qualityView.setBackgroundResource(R.drawable.bg_quality);
            qualityView.setText(quality);
            qualityView.setTextSize(12);
            qualityView.setTextColor(qualityColor);
            qualityView.setLayoutParams(qualityLayoutParams);
            holder.qualitiesContentView.addView(qualityView);
        }
        Picasso.with(context).load(model.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(@Nullable final Collection<LostNewsModel> data) {
        this.data.clear();
        if (data != null) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }
}

class LostNewsViewHolder extends RecyclerView.ViewHolder {

    final OnItemClickListenerWrapper listenerWrapper;

    @InjectView(R.id.title)
    TextView  titleView;
    @InjectView(R.id.description)
    TextView  descriptionView;
    @InjectView(R.id.seasonEpisode)
    TextView  seasonEpisodeView;
    @InjectView(R.id.pubDate)
    TextView  pubDateView;
    @InjectView(R.id.qualitiesContent)
    ViewGroup qualitiesContentView;
    @InjectView(R.id.image)
    ImageView imageView;

    LostNewsViewHolder(@NonNull final View itemView, @Nullable final LostNewsAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        listenerWrapper = new OnItemClickListenerWrapper(onItemClickListener);
        ButterKnife.inject(this, itemView);
        ButterKnife.findById(itemView, R.id.clickable).setOnClickListener(listenerWrapper);
    }

    void setLostNewsModel(@NonNull final LostNewsModel lostNewsModel) {
        listenerWrapper.setLostNewsModel(lostNewsModel);
    }

    static class OnItemClickListenerWrapper implements View.OnClickListener {

        final LostNewsAdapter.OnItemClickListener onItemClickListener;

        LostNewsModel lostNewsModel;

        public OnItemClickListenerWrapper(@Nullable final LostNewsAdapter.OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        public void setLostNewsModel(@NonNull final LostNewsModel lostNewsModel) {
            this.lostNewsModel = lostNewsModel;
        }

        @Override
        public void onClick(@NonNull final View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(lostNewsModel);
            }
        }
    }
}
