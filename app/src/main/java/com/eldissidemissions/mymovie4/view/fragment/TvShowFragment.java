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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eldissidemissions.mymovie4.R;
import com.eldissidemissions.mymovie4.model.TvShowModel;
import com.eldissidemissions.mymovie4.view.activity.DetailTvShowActivity;
import com.eldissidemissions.mymovie4.view.adapter.RecyclerTvShowAdapter;
import com.eldissidemissions.mymovie4.viewmodel.TvShowViewModel;
import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment implements RecyclerTvShowAdapter.OnItemClickCallback{
    private GoogleProgressBar googleProgressBar;
    private TvShowViewModel tvShowViewModel;
    private RecyclerTvShowAdapter recyclerTvShowAdapter;
    private ArrayList<TvShowModel> tvShowModels = new ArrayList<>();

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        googleProgressBar = view.findViewById(R.id.google_progress_bar_tv_show);
        RecyclerView recyclerViewTvShow = view.findViewById(R.id.rv_tv_show);
        recyclerViewTvShow.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerTvShowAdapter = new RecyclerTvShowAdapter(tvShowModels, this);
        recyclerTvShowAdapter.notifyDataSetChanged();
        recyclerViewTvShow.setAdapter(recyclerTvShowAdapter);

        if (getActivity() != null){
            tvShowViewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(TvShowViewModel.class);
            tvShowViewModel.setTvShow();
            showLoadingTvShow(true);
        }


        tvShowViewModel.getTvShow().observe(getActivity(), new Observer<ArrayList<TvShowModel>>() {
            @Override
            public void onChanged(ArrayList<TvShowModel> tvShowModels) {
                if (tvShowModels != null){
                    recyclerTvShowAdapter.setData(tvShowModels);
                    showLoadingTvShow(false);
                }
            }
        });
    }


    private void showLoadingTvShow(Boolean state){
        if (state){
            googleProgressBar.setVisibility(View.VISIBLE);
        }else{
            googleProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void clickable(int position) {
        Intent tvShowIntent = new Intent(getActivity(), DetailTvShowActivity.class);
        tvShowIntent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, tvShowModels.get(position));
        startActivity(tvShowIntent);
    }
}
