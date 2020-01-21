package com.eldissidemissions.mymovie4.database;

import android.provider.BaseColumns;

public class DatabaseContractTvShow {
    public static final class TvShowColumns implements BaseColumns {
        static final String TABLE_NAME_TV_SHOW = "tv_show";
        public static final String IMAGE = "image";
        public static final String NAME = "name";
        public static final String RATING = "rating";
        public static final String ORIGINAL_TITLE = "original_title";
        public static final String LANGUAGE = "language";
        public static final String RELEASE_DATE = "release_date";
        public static final String OVERVIEW = "overview";
    }
}
