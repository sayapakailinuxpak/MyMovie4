package com.eldissidemissions.mymovie4.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eldissidemissions.mymovie4.R;
import com.eldissidemissions.mymovie4.model.TvShowModel;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class RecyclerTvShowAdapter extends RecyclerView.Adapter<RecyclerTvShowAdapter.ViewHolder> {
    private ArrayList<TvShowModel> tvShows;
    private OnItemClickCallback onItemClickCallback;

    public RecyclerTvShowAdapter(ArrayList<TvShowModel> tvShows, OnItemClickCallback onItemClickCallback){
        this.tvShows = tvShows;
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setData(ArrayList<TvShowModel> tvShowModels){
        tvShows.clear();
        tvShows.addAll(tvShowModels);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerTvShowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv_show, parent, false);
        return new ViewHolder(view, onItemClickCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerTvShowAdapter.ViewHolder holder, int position) {
        holder.bind(tvShows.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView roundedImageViewTvShowPhoto;
        private TextView textViewTvShowName;
        OnItemClickCallback onItemClickCallback;

        private ViewHolder(@NonNull View itemView, final OnItemClickCallback onItemClickCallback) {
            super(itemView);
            roundedImageViewTvShowPhoto = itemView.findViewById(R.id.riv_tv_show);
            textViewTvShowName = itemView.findViewById(R.id.textview_tv_show_name);

            this.onItemClickCallback = onItemClickCallback;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickCallback.clickable(getAdapterPosition());
                }
            });
        }

        void bind(TvShowModel tvShowModel){
            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/original/" + tvShowModel.getTvShowPhoto())
                    .into(roundedImageViewTvShowPhoto);
            textViewTvShowName.setText(tvShowModel.getTvShowName());
        }
    }

    public interface OnItemClickCallback{
        void clickable(int position);
    }
}

