package com.eldissidemissions.mymovie4.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.eldissidemissions.mymovie4.R;
import com.eldissidemissions.mymovie4.view.adapter.SectionsFavoritePagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class FavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        SectionsFavoritePagerAdapter sectionsFavoritePagerAdapter = new SectionsFavoritePagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewpagerFav);
        viewPager.setAdapter(sectionsFavoritePagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabsFav);
        tabLayout.setupWithViewPager(viewPager);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.favorite);
            getSupportActionBar().setElevation(0);
        }

    }

}
