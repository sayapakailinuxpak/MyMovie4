package com.eldissidemissions.mymovie4.view.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.eldissidemissions.mymovie4.R;
import com.eldissidemissions.mymovie4.database.TvShowHelper;
import com.eldissidemissions.mymovie4.model.TvShowModel;
import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class DetailTvShowActivity extends AppCompatActivity {
    public static final String EXTRA_TV_SHOW = "extra_tv_show"; //Key
    private RoundedImageView roundedImageViewTvShowPhoto;
    private TextView textViewRating, textViewOriginalTitle, textViewLanguage, textViewMovieReleaseDate, textViewMovieOverview;
    private GoogleProgressBar googleProgressBarDetail;
    TvShowModel tvShowModel;
    TvShowHelper tvShowHelper;
    private boolean isFavorite;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);
        initUI();
        tvShowModel = getIntent().getParcelableExtra(EXTRA_TV_SHOW);

        /*open connection to database*/
        tvShowHelper = TvShowHelper.getInstance(getApplicationContext());
        tvShowHelper.openConnection();

        showLoading(true);
        if (tvShowModel != null){
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/original/"+ tvShowModel.getTvShowPhoto())
                    .into(roundedImageViewTvShowPhoto);
            textViewRating.setText(String.valueOf(tvShowModel.getTvShowRating()));
            textViewOriginalTitle.setText(tvShowModel.getTvShowOriginalName());
            textViewLanguage.setText(tvShowModel.getTvShowLanguage());
            textViewMovieReleaseDate.setText(tvShowModel.getTvShowReleaseDate());
            textViewMovieOverview.setText(tvShowModel.getTvShowOverview());
        }

        isFavorite = false;
        check();
    }

    private void initUI(){
        roundedImageViewTvShowPhoto = findViewById(R.id.imageview_tv_show);
        textViewRating = findViewById(R.id.textview_tv_show_rating);
        textViewOriginalTitle = findViewById(R.id.textview_original_title_tv_show);
        textViewLanguage = findViewById(R.id.textview_language_tv_show);
        textViewMovieReleaseDate = findViewById(R.id.textview_release_tv_show);
        textViewMovieOverview = findViewById(R.id.textview_tv_show_overview);
        googleProgressBarDetail = findViewById(R.id.google_progress_bar_detail_tv_show);


    }

    private void showLoading(Boolean state){
        if (state){
            googleProgressBarDetail.setVisibility(View.VISIBLE);
        }else {
            googleProgressBarDetail.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fav_menu, menu);
        this.menu = menu;

        setIconState();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_favorite){
            onPressedFavoriteButton();
        }
        return super.onOptionsItemSelected(item);
    }

    private void check(){
        ArrayList<TvShowModel> all = tvShowHelper.queryAll();
        for (TvShowModel model: all){
            if (this.tvShowModel.getId() == model.getId()){
                isFavorite = true;
            }
            if (isFavorite){
                break;
            }
        }
    }



    private void onPressedFavoriteButton(){
        if (isFavorite){
            Toast.makeText(this, "Removed to favorite :(", Toast.LENGTH_SHORT).show();
            tvShowHelper.delete(String.valueOf(tvShowModel.getId()));
        }else {
            Toast.makeText(this, "Added to favorite :)", Toast.LENGTH_SHORT).show();
            tvShowHelper.insert(tvShowModel);
        }

        isFavorite = !isFavorite;
        setIconState();
    }

    private void setIconState(){
        if (isFavorite){
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_favorite_white_24dp));
        }else{
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_favorite_border_white_24dp));
        }

    }

    /*destroy connecttion*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        tvShowHelper.closeConnection();
    }


}

