package com.eldissidemissions.mymovie4.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eldissidemissions.mymovie4.R;
import com.eldissidemissions.mymovie4.model.TvShowModel;
import com.eldissidemissions.mymovie4.view.activity.DetailTvShowActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerTvShowFavoriteAdapter extends RecyclerView.Adapter<RecyclerTvShowFavoriteAdapter.ViewHolder> {
    private ArrayList<TvShowModel> tvShowModels;
    private Context context;

    public RecyclerTvShowFavoriteAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<TvShowModel> getTvShowModels() {
        return tvShowModels;
    }

    public void setTvShowModels(ArrayList<TvShowModel> tvShowModels) {
        this.tvShowModels = tvShowModels;
    }

    public void setDataTvShowFavorite(ArrayList<TvShowModel> tvShowModels) {
        this.tvShowModels.clear();
        this.tvShowModels.addAll(tvShowModels);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerTvShowFavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv_show_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerTvShowFavoriteAdapter.ViewHolder holder, final int position) {
        TvShowModel tvShowModel = tvShowModels.get(position);
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/original/"+ tvShowModel.getTvShowPhoto())
                .into(holder.circleImageViewImageFavorite);
        holder.textViewMovieNameFavorite.setText(tvShowModel.getTvShowName());
        holder.textViewMovieRatingFavorite.setText(String.valueOf(tvShowModel.getTvShowRating()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailTvShowActivity.class);
                intent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, getTvShowModels().get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvShowModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageViewImageFavorite;
        TextView textViewMovieNameFavorite, textViewMovieRatingFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageViewImageFavorite = itemView.findViewById(R.id.civ_tv_show_favorite);
            textViewMovieNameFavorite = itemView.findViewById(R.id.textview_tv_show_name_favorite);
            textViewMovieRatingFavorite = itemView.findViewById(R.id.textview_tv_shows_rating_favorite);
        }
    }

}
