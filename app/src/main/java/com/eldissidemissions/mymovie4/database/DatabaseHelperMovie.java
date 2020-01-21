package com.eldissidemissions.mymovie4.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.eldissidemissions.mymovie4.database.DatabaseContractMovie.MovieColumns;
//import static com.eldissidemissions.mymovie4.database.DatabaseContractMovie.MovieColumns.TABLE_NAME_MOVIE;

public class DatabaseHelperMovie extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db_movie_favorite";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " %s TEXT NULL,"
                    + " %s TEXT NULL,"
                    + " %s TEXT NULL,"
                    + " %s TEXT NULL,"
                    + " %s TEXT NULL,"
                    + " %s TEXT NULL,"
                    + " %s TEXT NULL)",
            DatabaseContractMovie.TABLE_NAME_MOVIE,
            MovieColumns._ID,
            MovieColumns.IMAGE,
            MovieColumns.NAME,
            MovieColumns.RATING,
            MovieColumns.ORIGINAL_TITLE,
            MovieColumns.LANGUAGE,
            MovieColumns.RELEASE_DATE,
            MovieColumns.OVERVIEW);

    public DatabaseHelperMovie(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContractMovie.TABLE_NAME_MOVIE);
        onCreate(db);
    }
}
