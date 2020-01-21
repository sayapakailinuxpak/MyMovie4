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
import com.eldissidemissions.mymovie4.model.MovieModel;
import com.eldissidemissions.mymovie4.view.activity.DetailMovieActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class RecyclerMovieFavoriteAdapter extends RecyclerView.Adapter<RecyclerMovieFavoriteAdapter.ViewHolder> {
    //private ArrayList<MovieModel> movieModels;
    private List<MovieModel> movieModels;
    private Context context;

    public RecyclerMovieFavoriteAdapter(Context context) {
        this.context = context;
    }

    public List<MovieModel> getMovieModels() {
        return movieModels;
    }

    public void setMovieModels(List<MovieModel> movieModels) {
        this.movieModels = movieModels;
    }

    public void setDataFavorite(List<MovieModel> movieModels) {
//        this.movieModels.clear();
//        this.movieModels.addAll(movieModels);
//        notifyDataSetChanged();
        if (movieModels.size() > 0){
            this.movieModels.clear();
        }
        this.movieModels.addAll(movieModels);
        notifyDataSetChanged();
    }




    @NonNull
    @Override
    public RecyclerMovieFavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerMovieFavoriteAdapter.ViewHolder holder, final int position) {
        MovieModel movieModel = movieModels.get(position);
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/original/"+ movieModel.getMoviePhoto())
                .into(holder.circleImageViewImageFavorite);
        holder.textViewMovieNameFavorite.setText(movieModel.getMovieName());
        holder.textViewMovieRatingFavorite.setText(String.valueOf(movieModel.getMovieRating()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, getMovieModels().get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageViewImageFavorite;
        TextView textViewMovieNameFavorite, textViewMovieRatingFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageViewImageFavorite = itemView.findViewById(R.id.civ_movie_favorite);
            textViewMovieNameFavorite = itemView.findViewById(R.id.textview_movie_name_favorite);
            textViewMovieRatingFavorite = itemView.findViewById(R.id.textview_movie_rating_favorite);
        }
    }

}
