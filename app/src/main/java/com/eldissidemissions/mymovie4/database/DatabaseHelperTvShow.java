package com.eldissidemissions.mymovie4.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.eldissidemissions.mymovie4.database.DatabaseContractTvShow.TvShowColumns.TABLE_NAME_TV_SHOW;

public class DatabaseHelperTvShow extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db_tv_show_favorite";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_TV_SHOW = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " %s TEXT NULL,"
                    + " %s TEXT NULL,"
                    + " %s TEXT NULL,"
                    + " %s TEXT NULL,"
                    + " %s TEXT NULL,"
                    + " %s TEXT NULL,"
                    + " %s TEXT NULL)",
            TABLE_NAME_TV_SHOW,
            DatabaseContractTvShow.TvShowColumns._ID,
            DatabaseContractTvShow.TvShowColumns.IMAGE,
            DatabaseContractTvShow.TvShowColumns.NAME,
            DatabaseContractTvShow.TvShowColumns.RATING,
            DatabaseContractTvShow.TvShowColumns.ORIGINAL_TITLE,
            DatabaseContractTvShow.TvShowColumns.LANGUAGE,
            DatabaseContractTvShow.TvShowColumns.RELEASE_DATE,
            DatabaseContractTvShow.TvShowColumns.OVERVIEW);

    public DatabaseHelperTvShow(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TV_SHOW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TV_SHOW);
        onCreate(db);
    }
}

