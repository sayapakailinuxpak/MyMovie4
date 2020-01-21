package com.eldissidemissions.mymovie4.viewmodel;

import android.os.Parcel;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eldissidemissions.mymovie4.BuildConfig;
import com.eldissidemissions.mymovie4.model.TvShowModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TvShowViewModel extends ViewModel {
    private static final String API_KEY = BuildConfig.TMDB_API_KEY;
    private MutableLiveData<ArrayList<TvShowModel>> listTvShow = new MutableLiveData<>();



    public void setTvShow(){
        final AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        final ArrayList<TvShowModel> tvShowModels = new ArrayList<>();

        String url = "https://api.themoviedb.org/3/discover/tv?api_key="+API_KEY+"&language=en-US";
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() { //request, response
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray jsonArray = responseObject.getJSONArray("results");

                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject jsonObjectTvShow = jsonArray.getJSONObject(i);
                        TvShowModel tvShowModel = new TvShowModel(Parcel.obtain());
                        tvShowModel.setId(jsonObjectTvShow.getInt("id"));
                        tvShowModel.setTvShowPhoto(jsonObjectTvShow.getString("poster_path"));
                        tvShowModel.setTvShowName(jsonObjectTvShow.getString("name"));
                        tvShowModel.setTvShowRating(jsonObjectTvShow.getDouble("vote_average"));
                        tvShowModel.setTvShowOriginalName(jsonObjectTvShow.getString("original_name"));
                        tvShowModel.setTvShowLanguage(jsonObjectTvShow.getString("original_language"));
                        tvShowModel.setTvShowReleaseDate(jsonObjectTvShow.getString("first_air_date"));
                        tvShowModel.setTvShowOverview(jsonObjectTvShow.getString("overview"));
                        tvShowModels.add(tvShowModel);
                    }
                    listTvShow.postValue(tvShowModels);
                }catch (Exception e){
                    Log.d("Exception", e.toString());
//                    Toast.makeText(getApplication().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Exception", error.toString());
//                Toast.makeText(getApplication().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public LiveData<ArrayList<TvShowModel>> getTvShow(){
        return listTvShow;
    }
}
