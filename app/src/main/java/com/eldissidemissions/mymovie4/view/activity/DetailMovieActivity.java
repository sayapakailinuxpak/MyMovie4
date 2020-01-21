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
import com.eldissidemissions.mymovie4.database.MovieHelper;
import com.eldissidemissions.mymovie4.model.MovieModel;
import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie"; //Key
    private RoundedImageView roundedImageViewMoviePhoto;
    private TextView textViewRating, textViewOriginalTitle, textViewLanguage, textViewMovieReleaseDate, textViewMovieOverview;
    private GoogleProgressBar googleProgressBarDetail;
    MovieModel movieModel;
    MovieHelper movieHelper;
    private boolean isFavorite;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        initUI();
        movieModel = getIntent().getParcelableExtra(EXTRA_MOVIE);

        /*open connection to database*/
        movieHelper = MovieHelper.getInstance(getApplicationContext());
        movieHelper.openConnection();

        showLoading(true);
        if (movieModel != null) {
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/original/" + movieModel.getMoviePhoto())
                    .into(roundedImageViewMoviePhoto);
            textViewRating.setText(String.valueOf(movieModel.getMovieRating()));
            textViewOriginalTitle.setText(movieModel.getMovieOriginalTitle());
            textViewLanguage.setText(movieModel.getMovieLanguage());
            textViewMovieReleaseDate.setText(movieModel.getMovieReleaseDate());
            textViewMovieOverview.setText(movieModel.getMovieOverview());
        }

        isFavorite = false;
        check();
    }

    private void initUI(){
        roundedImageViewMoviePhoto = findViewById(R.id.imageview_movie);
        textViewRating = findViewById(R.id.textview_movie_rating);
        textViewOriginalTitle = findViewById(R.id.textview_original_title_movie);
        textViewLanguage = findViewById(R.id.textview_language_movie);
        textViewMovieReleaseDate = findViewById(R.id.textview_release_movie);
        textViewMovieOverview = findViewById(R.id.textview_movie_overview);

        googleProgressBarDetail = findViewById(R.id.google_progress_bar_detail);

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
        ArrayList<MovieModel> all = movieHelper.queryAll();
        for (MovieModel model: all){
            if (this.movieModel.getId() == model.getId()){
                isFavorite = true;
            }
            if (isFavorite){
                break;
            }
        }
    }



    private void onPressedFavoriteButton(){
        if (isFavorite){
            movieHelper.delete(String.valueOf(movieModel.getId()));
            Toast.makeText(this, "Removed from favorite :(", Toast.LENGTH_SHORT).show();
        }else {
            movieHelper.insert(movieModel);
            Toast.makeText(this, "Added to favorite :)", Toast.LENGTH_SHORT).show();
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
        movieHelper.closeConnection();
    }

/*    private void saveState(boolean isFavorite){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("saved", isFavorite);
        editor.commit();
    }

    public boolean getState(){
        return sharedPreferences.getBoolean("saved", false);
    }
    */

}
