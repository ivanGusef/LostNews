package com.ivangusef.lostnews.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    private final Context             context;
    private final List<LostNewsModel> data;

    public LostNewsAdapter(@NonNull final Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    @Override
    public LostNewsViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new LostNewsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_lost_news, parent, false));
    }

    @Override
    public void onBindViewHolder(final LostNewsViewHolder holder, final int position) {
        final LostNewsModel model = data.get(position);
        holder.titleView.setText(model.getTitle());
        holder.descriptionView.setText(model.getDescription());
        holder.seasonEpisodeView.setText(model.getSeasonEpisode());
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

    @InjectView(R.id.title)
    TextView  titleView;
    @InjectView(R.id.description)
    TextView  descriptionView;
    @InjectView(R.id.seasonEpisode)
    TextView  seasonEpisodeView;
    @InjectView(R.id.image)
    ImageView imageView;

    public LostNewsViewHolder(@NonNull final View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }
}
