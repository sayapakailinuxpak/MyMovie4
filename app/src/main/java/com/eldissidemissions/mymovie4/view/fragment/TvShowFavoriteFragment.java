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
import com.eldissidemissions.mymovie4.database.TvShowHelper;
import com.eldissidemissions.mymovie4.model.TvShowModel;
import com.eldissidemissions.mymovie4.view.adapter.RecyclerTvShowFavoriteAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFavoriteFragment extends Fragment {
    private ArrayList<TvShowModel> tvShowModels= new ArrayList<>();
    private RecyclerTvShowFavoriteAdapter recyclerTvShowFavoriteAdapter;
    private RecyclerView recyclerViewTvShowFavorite;
    private TvShowHelper tvShowHelper;


    public TvShowFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvShowHelper = new TvShowHelper(getContext());
        tvShowHelper.openConnection();

        recyclerViewTvShowFavorite = view.findViewById(R.id.rv_tv_show_favorite);
        recyclerViewTvShowFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewTvShowFavorite.setHasFixedSize(true);

        recyclerTvShowFavoriteAdapter = new RecyclerTvShowFavoriteAdapter(view.getContext());
        recyclerViewTvShowFavorite.setAdapter(recyclerTvShowFavoriteAdapter);
        recyclerTvShowFavoriteAdapter.setTvShowModels(tvShowModels);
    }

    @Override
    public void onResume() {
        super.onResume();
        tvShowModels = tvShowHelper.queryAll();
        recyclerTvShowFavoriteAdapter.setDataTvShowFavorite(tvShowModels);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tvShowHelper.closeConnection();
    }
}
