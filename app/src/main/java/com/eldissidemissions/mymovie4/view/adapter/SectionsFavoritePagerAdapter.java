package com.eldissidemissions.mymovie4.view.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.eldissidemissions.mymovie4.R;
import com.eldissidemissions.mymovie4.view.fragment.MovieFavoriteFragment;
import com.eldissidemissions.mymovie4.view.fragment.TvShowFavoriteFragment;

public class SectionsFavoritePagerAdapter extends FragmentPagerAdapter {
    private final Context context;
    public SectionsFavoritePagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    @StringRes
    private int[] TAB_TITLES = new int[]{
            R.string.tab_movie_label,
            R.string.tab_tv_show_label
    };

    @Nullable
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0 :
                fragment = new MovieFavoriteFragment();
                break;
            case 1 :
                fragment = new TvShowFavoriteFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }
}

