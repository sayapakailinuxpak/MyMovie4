package com.eldissidemissions.mymovie4.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eldissidemissions.mymovie4.R;
import com.eldissidemissions.mymovie4.model.MovieModel;
import com.eldissidemissions.mymovie4.view.activity.DetailMovieActivity;
import com.eldissidemissions.mymovie4.view.adapter.RecyclerMovieAdapter;
import com.eldissidemissions.mymovie4.viewmodel.MovieViewModel;
import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements RecyclerMovieAdapter.OnItemClickCallback{

    private GoogleProgressBar googleProgressBar;
    private MovieViewModel movieViewModel;
    private RecyclerMovieAdapter movieAdapter;
    private ArrayList<MovieModel> movieModels = new ArrayList<>();

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        googleProgressBar = view.findViewById(R.id.google_progress_bar);
        RecyclerView recyclerViewMovie = view.findViewById(R.id.rv_movie);
        recyclerViewMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieAdapter = new RecyclerMovieAdapter(movieModels, this);
        movieAdapter.notifyDataSetChanged();
        recyclerViewMovie.setAdapter(movieAdapter);
        recyclerViewMovie.setHasFixedSize(true);

        if (getActivity() != null){
            movieViewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);
            movieViewModel.setMovie();
            showLoading(true);
        }


        movieViewModel.getMovie().observe(getActivity(), new Observer<ArrayList<MovieModel>>() {
            @Override
            public void onChanged(ArrayList<MovieModel> items) {
                if (items != null){
                    movieModels = items;
                    movieAdapter.setData(items);
                    showLoading(false);
                }
            }
        });
    }


    private void showLoading(Boolean state){
        if (state){
            googleProgressBar.setVisibility(View.VISIBLE);
        }else{
            googleProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void clickable(int position) {
        Intent movieIntent = new Intent(getActivity(), DetailMovieActivity.class);
        movieIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movieModels.get(position));
        startActivity(movieIntent);
    }


}
