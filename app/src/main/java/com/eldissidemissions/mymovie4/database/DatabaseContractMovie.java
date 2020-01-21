package com.eldissidemissions.mymovie4.database;

import android.provider.BaseColumns;

public class DatabaseContractMovie {

    public static final String TABLE_NAME_MOVIE = "movie";
    public static final class MovieColumns implements BaseColumns{
        //static final String TABLE_NAME_MOVIE = "movie";
        public static final String IMAGE = "image";
        public static final String NAME = "name";
        public static final String RATING = "rating";
        public static final String ORIGINAL_TITLE = "original_title";
        public static final String LANGUAGE = "language";
        public static final String RELEASE_DATE = "release_date";
        public static final String OVERVIEW = "overview";
    }
}
