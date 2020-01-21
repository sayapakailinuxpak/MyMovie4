package com.eldissidemissions.mymovie4.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.eldissidemissions.mymovie4.model.TvShowModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.eldissidemissions.mymovie4.database.DatabaseContractTvShow.TvShowColumns.IMAGE;
import static com.eldissidemissions.mymovie4.database.DatabaseContractTvShow.TvShowColumns.LANGUAGE;
import static com.eldissidemissions.mymovie4.database.DatabaseContractTvShow.TvShowColumns.NAME;
import static com.eldissidemissions.mymovie4.database.DatabaseContractTvShow.TvShowColumns.ORIGINAL_TITLE;
import static com.eldissidemissions.mymovie4.database.DatabaseContractTvShow.TvShowColumns.OVERVIEW;
import static com.eldissidemissions.mymovie4.database.DatabaseContractTvShow.TvShowColumns.RATING;
import static com.eldissidemissions.mymovie4.database.DatabaseContractTvShow.TvShowColumns.RELEASE_DATE;
import static com.eldissidemissions.mymovie4.database.DatabaseContractTvShow.TvShowColumns.TABLE_NAME_TV_SHOW;


public class TvShowHelper {
    private static final String DATABASE_TABLE = TABLE_NAME_TV_SHOW;
    private static DatabaseHelperTvShow databaseHelperTvShow;
    private static TvShowHelper INSTANCE;
    private static SQLiteDatabase sqLiteDatabase;

    public TvShowHelper(Context context) {
        databaseHelperTvShow = new DatabaseHelperTvShow(context);
    }

    public static TvShowHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new TvShowHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void openConnection() throws SQLException {
        sqLiteDatabase = databaseHelperTvShow.getWritableDatabase();
    }

    public void closeConnection(){
        databaseHelperTvShow.close();
        if (sqLiteDatabase.isOpen()){
            databaseHelperTvShow.close();
        }
    }

    public ArrayList<TvShowModel> queryAll(){
        ArrayList<TvShowModel> tvShowModelArrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();

        TvShowModel tvShowModel;
        if (cursor.getCount()>0){
            do {
                tvShowModel = new TvShowModel();
                tvShowModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                tvShowModel.setTvShowPhoto(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                tvShowModel.setTvShowName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                tvShowModel.setTvShowRating(cursor.getDouble(cursor.getColumnIndexOrThrow(RATING)));
                tvShowModel.setTvShowOriginalName(cursor.getString(cursor.getColumnIndexOrThrow(ORIGINAL_TITLE)));
                tvShowModel.setTvShowLanguage(cursor.getString(cursor.getColumnIndexOrThrow(LANGUAGE)));
                tvShowModel.setTvShowReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                tvShowModel.setTvShowOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));

                tvShowModelArrayList.add(tvShowModel);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return tvShowModelArrayList;
    }

    public long insert(TvShowModel tvShowModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(_ID, tvShowModel.getId());
        contentValues.put(IMAGE, tvShowModel.getTvShowPhoto());
        contentValues.put(NAME, tvShowModel.getTvShowName());
        contentValues.put(RATING, tvShowModel.getTvShowRating());
        contentValues.put(ORIGINAL_TITLE, tvShowModel.getTvShowOriginalName());
        contentValues.put(LANGUAGE, tvShowModel.getTvShowLanguage());
        contentValues.put(RELEASE_DATE, tvShowModel.getTvShowReleaseDate());
        contentValues.put(OVERVIEW, tvShowModel.getTvShowOverview());

        return sqLiteDatabase.insert(DATABASE_TABLE, null, contentValues);
    }

    public int delete(String id){
        return sqLiteDatabase.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});

    }
}
