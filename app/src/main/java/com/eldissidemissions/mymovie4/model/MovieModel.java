package com.eldissidemissions.mymovie4.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable {
    private int id;
    private String moviePhoto;
    private String movieName;
    private double movieRating;
    private String movieOriginalTitle;
    private String movieLanguage;
    private String movieReleaseDate;
    private String movieOverview;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoviePhoto() {
        return moviePhoto;
    }

    public void setMoviePhoto(String moviePhoto) {
        this.moviePhoto = moviePhoto;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public double getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(double movieRating) {
        this.movieRating = movieRating;
    }

    public String getMovieOriginalTitle() {
        return movieOriginalTitle;
    }

    public void setMovieOriginalTitle(String movieOriginalTitle) {
        this.movieOriginalTitle = movieOriginalTitle;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public MovieModel() {
    }

    public MovieModel(int id, String moviePhoto, String movieName, double movieRating, String movieOriginalTitle, String movieLanguage, String movieReleaseDate, String movieOverview) {
        this.id = id;
        this.moviePhoto = moviePhoto;
        this.movieName = movieName;
        this.movieRating = movieRating;
        this.movieOriginalTitle = movieOriginalTitle;
        this.movieLanguage = movieLanguage;
        this.movieReleaseDate = movieReleaseDate;
        this.movieOverview = movieOverview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.movieName);
        dest.writeString(this.moviePhoto);
        dest.writeDouble(this.movieRating);
        dest.writeString(this.movieOriginalTitle);
        dest.writeString(this.movieLanguage);
        dest.writeString(this.movieReleaseDate);
        dest.writeString(this.movieOverview);
    }

    public MovieModel(Parcel in) {
        this.id = in.readInt();
        this.movieName = in.readString();
        this.moviePhoto = in.readString();
        this.movieRating = in.readDouble();
        this.movieOriginalTitle = in.readString();
        this.movieLanguage = in.readString();
        this.movieReleaseDate = in.readString();
        this.movieOverview = in.readString();
    }

    public static final Parcelable.Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };
}

