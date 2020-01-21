package com.eldissidemissions.mymovie4.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eldissidemissions.mymovie4.R;
import com.eldissidemissions.mymovie4.database.MovieHelper;
import com.eldissidemissions.mymovie4.model.MovieModel;
import com.eldissidemissions.mymovie4.view.adapter.RecyclerMovieFavoriteAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavoriteFragment extends Fragment  {
    //private ArrayList<MovieModel> movieModels = new ArrayList<>();
    private List<MovieModel> movieModels = new ArrayList<>();
    private RecyclerMovieFavoriteAdapter recyclerMovieFavoriteAdapter;
    private RecyclerView recyclerViewFavorite;
    private MovieHelper movieHelper;


    public MovieFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieHelper = new MovieHelper(getContext());
        movieHelper.openConnection();

        recyclerViewFavorite = view.findViewById(R.id.rv_movie_favorite);
        recyclerViewFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewFavorite.setHasFixedSize(true);

        recyclerMovieFavoriteAdapter = new RecyclerMovieFavoriteAdapter(view.getContext());
        recyclerViewFavorite.setAdapter(recyclerMovieFavoriteAdapter);
        recyclerMovieFavoriteAdapter.setMovieModels(movieModels);
    }

    @Override
    public void onResume() {
        super.onResume();
        movieModels = movieHelper.queryAll();
        recyclerMovieFavoriteAdapter.setDataFavorite(movieModels);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movieHelper.closeConnection();
    }
}
