package com.eldissidemissions.mymovie4.viewmodel;

import android.os.Parcel;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eldissidemissions.mymovie4.BuildConfig;
import com.eldissidemissions.mymovie4.model.MovieModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieViewModel extends ViewModel {
    private static final String API_KEY = BuildConfig.TMDB_API_KEY;
    private MutableLiveData<ArrayList<MovieModel>> listMovie = new MutableLiveData<>();


    public void setMovie(){
        final AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        final ArrayList<MovieModel> movieModels = new ArrayList<>();
        String url = "http://api.themoviedb.org/3/discover/movie?api_key="+ API_KEY +"&language=en-US";
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray jsonArray = responseObject.getJSONArray("results");

                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject jsonObjectMovie = jsonArray.getJSONObject(i);
                        MovieModel movieModel = new MovieModel(Parcel.obtain());
                        movieModel.setId(jsonObjectMovie.getInt("id"));
                        movieModel.setMoviePhoto(jsonObjectMovie.getString("poster_path"));
                        movieModel.setMovieName(jsonObjectMovie.getString("title"));
                        movieModel.setMovieRating(jsonObjectMovie.getDouble("vote_average"));
                        movieModel.setMovieOriginalTitle(jsonObjectMovie.getString("original_title"));
                        movieModel.setMovieLanguage(jsonObjectMovie.getString("original_language"));
                        movieModel.setMovieReleaseDate(jsonObjectMovie.getString("release_date"));
                        movieModel.setMovieOverview(jsonObjectMovie.getString("overview"));
                        movieModels.add(movieModel);
                    }
                    listMovie.postValue(movieModels);
                }catch (Exception e){
                    Log.d("Exception", e.toString());
//                    Toast.makeText(getApplication().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Exception", error.toString());
//                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public LiveData<ArrayList<MovieModel>> getMovie(){
        return listMovie;
    }
}


