package com.eldissidemissions.mymovie4.view.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eldissidemissions.mymovie4.R;
import com.eldissidemissions.mymovie4.model.MovieModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class RecyclerMovieAdapter extends RecyclerView.Adapter<RecyclerMovieAdapter.ViewHolder> {
    private ArrayList<MovieModel> movies;
    private OnItemClickCallback onItemClickCallback;

    public RecyclerMovieAdapter(ArrayList<MovieModel> movies, OnItemClickCallback onItemClickCallback){
        this.movies = movies;
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setData(ArrayList<MovieModel> movieModels) {
        if (movieModels.size() > 0){
            this.movies.clear();
        }
        this.movies.addAll(movieModels);
        notifyDataSetChanged();
    }




    @NonNull
    @Override
    public RecyclerMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view, onItemClickCallback);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerMovieAdapter.ViewHolder holder, final int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView circleImageViewMoviePhoto;
        private TextView textViewMovieName, textViewMovieRating;
        OnItemClickCallback onItemClickCallback;


        private ViewHolder(@NonNull View itemView, final OnItemClickCallback onItemClickCallback) {
            super(itemView);
            circleImageViewMoviePhoto = itemView.findViewById(R.id.civ_movie);
            textViewMovieName = itemView.findViewById(R.id.textview_movie_name);
            textViewMovieRating = itemView.findViewById(R.id.textview_movie_rating);

            this.onItemClickCallback = onItemClickCallback;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickCallback.clickable(getAdapterPosition());
                }
            });

        }

        void bind(MovieModel movieModel){

            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/original/"+ movieModel.getMoviePhoto())
                    .apply(new RequestOptions().override(90, 90))
                    .into(circleImageViewMoviePhoto);
            textViewMovieName.setText(movieModel.getMovieName());
            textViewMovieRating.setText(String.valueOf(movieModel.getMovieRating()));
        }

    }


    public interface OnItemClickCallback{
        void clickable(int position);

    }
}



