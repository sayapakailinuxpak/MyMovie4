package com.eldissidemissions.mymovie4.model;

import android.os.Parcel;
import android.os.Parcelable;



public class TvShowModel implements Parcelable {
    private int id;
    private String tvShowPhoto;
    private String tvShowName;
    private double tvShowRating;
    private String tvShowOriginalName;
    private String tvShowLanguage;
    private String tvShowReleaseDate;
    private String tvShowOverview;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTvShowPhoto() {
        return tvShowPhoto;
    }

    public void setTvShowPhoto(String tvShowPhoto) {
        this.tvShowPhoto = tvShowPhoto;
    }

    public String getTvShowName() {
        return tvShowName;
    }

    public void setTvShowName(String tvShowName) {
        this.tvShowName = tvShowName;
    }

    public double getTvShowRating() {
        return tvShowRating;
    }

    public void setTvShowRating(double tvShowRating) {
        this.tvShowRating = tvShowRating;
    }

    public String getTvShowOriginalName() {
        return tvShowOriginalName;
    }

    public void setTvShowOriginalName(String tvShowOriginalName) {
        this.tvShowOriginalName = tvShowOriginalName;
    }

    public String getTvShowLanguage() {
        return tvShowLanguage;
    }

    public void setTvShowLanguage(String tvShowLanguage) {
        this.tvShowLanguage = tvShowLanguage;
    }

    public String getTvShowReleaseDate() {
        return tvShowReleaseDate;
    }

    public void setTvShowReleaseDate(String tvShowReleaseDate) {
        this.tvShowReleaseDate = tvShowReleaseDate;
    }

    public String getTvShowOverview() {
        return tvShowOverview;
    }

    public void setTvShowOverview(String tvShowOverview) {
        this.tvShowOverview = tvShowOverview;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.tvShowName);
        dest.writeString(this.tvShowPhoto);
        dest.writeDouble(this.tvShowRating);
        dest.writeString(this.tvShowOriginalName);
        dest.writeString(this.tvShowLanguage);
        dest.writeString(this.tvShowReleaseDate);
        dest.writeString(this.tvShowOverview);
    }

    public TvShowModel(Parcel in){
        this.id = in.readInt();
        this.tvShowName = in.readString();
        this.tvShowPhoto = in.readString();
        this.tvShowRating = in.readDouble();
        this.tvShowOriginalName = in.readString();
        this.tvShowLanguage = in.readString();
        this.tvShowReleaseDate = in.readString();
        this.tvShowOverview = in.readString();
    }

    public TvShowModel() {
    }

    public static final Parcelable.Creator<TvShowModel> CREATOR = new Creator<TvShowModel>() {
        @Override
        public TvShowModel createFromParcel(Parcel source) {
            return new TvShowModel(source);
        }

        @Override
        public TvShowModel[] newArray(int size) {
            return new TvShowModel[size];
        }
    };


}

