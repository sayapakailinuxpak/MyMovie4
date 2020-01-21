package com.eldissidemissions.mymovie4.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.eldissidemissions.mymovie4.model.MovieModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.eldissidemissions.mymovie4.database.DatabaseContractMovie.MovieColumns.IMAGE;
import static com.eldissidemissions.mymovie4.database.DatabaseContractMovie.MovieColumns.LANGUAGE;
import static com.eldissidemissions.mymovie4.database.DatabaseContractMovie.MovieColumns.NAME;
import static com.eldissidemissions.mymovie4.database.DatabaseContractMovie.MovieColumns.ORIGINAL_TITLE;
import static com.eldissidemissions.mymovie4.database.DatabaseContractMovie.MovieColumns.OVERVIEW;
import static com.eldissidemissions.mymovie4.database.DatabaseContractMovie.MovieColumns.RATING;
import static com.eldissidemissions.mymovie4.database.DatabaseContractMovie.MovieColumns.RELEASE_DATE;
//import static com.eldissidemissions.mymovie4.database.DatabaseContractMovie.MovieColumns.TABLE_NAME_MOVIE;

public class MovieHelper {
    private static final String DATABASE_TABLE = DatabaseContractMovie.TABLE_NAME_MOVIE;
    private static DatabaseHelperMovie databaseHelperMovie;
    private static MovieHelper INSTANCE;
    private static SQLiteDatabase sqLiteDatabase;

    public MovieHelper(Context context) {
        databaseHelperMovie = new DatabaseHelperMovie(context);
    }

    public static MovieHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new MovieHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void openConnection() throws SQLException{
        sqLiteDatabase = databaseHelperMovie.getWritableDatabase();
    }

    public void closeConnection(){
        databaseHelperMovie.close();
        if (sqLiteDatabase.isOpen()){
            databaseHelperMovie.close();
        }
    }

    public ArrayList<MovieModel> queryAll(){
            ArrayList<MovieModel> movieModelArrayList = new ArrayList<>();
            Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE,
                    null,
                    null,
                    null,
                    null,
                    null,
                    _ID + " ASC",
                    null);
            cursor.moveToFirst();

            MovieModel movieModel;
            if (cursor.getCount()>0){
                do {
                    movieModel = new MovieModel();
                    movieModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                    movieModel.setMoviePhoto(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                    movieModel.setMovieName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                    movieModel.setMovieRating(cursor.getDouble(cursor.getColumnIndexOrThrow(RATING)));
                    movieModel.setMovieOriginalTitle(cursor.getString(cursor.getColumnIndexOrThrow(ORIGINAL_TITLE)));
                    movieModel.setMovieLanguage(cursor.getString(cursor.getColumnIndexOrThrow(LANGUAGE)));
                    movieModel.setMovieReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                    movieModel.setMovieOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));

                    movieModelArrayList.add(movieModel);
                    cursor.moveToNext();
                }while (!cursor.isAfterLast());
            }
            cursor.close();
            return movieModelArrayList;
    }

    public ArrayList<MovieModel> queryById(String id){
        ArrayList<MovieModel> movieModelArrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE,
                null,
                _ID + " = ?",
                new String[]{id},
                null,
                null,
                null);
        cursor.moveToFirst();

        MovieModel movieModel;
        if (cursor.getCount()>0){
            do {
                movieModel = new MovieModel();
                movieModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                movieModel.setMoviePhoto(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                movieModel.setMovieName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                movieModel.setMovieRating(cursor.getDouble(cursor.getColumnIndexOrThrow(RATING)));
                movieModel.setMovieOriginalTitle(cursor.getString(cursor.getColumnIndexOrThrow(ORIGINAL_TITLE)));
                movieModel.setMovieLanguage(cursor.getString(cursor.getColumnIndexOrThrow(LANGUAGE)));
                movieModel.setMovieReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                movieModel.setMovieOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));

                movieModelArrayList.add(movieModel);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return movieModelArrayList;
    }


    public long insert(MovieModel movieModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(_ID, movieModel.getId());
        contentValues.put(IMAGE, movieModel.getMoviePhoto());
        contentValues.put(NAME, movieModel.getMovieName());
        contentValues.put(RATING, movieModel.getMovieRating());
        contentValues.put(ORIGINAL_TITLE, movieModel.getMovieOriginalTitle());
        contentValues.put(LANGUAGE, movieModel.getMovieLanguage());
        contentValues.put(RELEASE_DATE, movieModel.getMovieReleaseDate());
        contentValues.put(OVERVIEW, movieModel.getMovieOverview());

        return sqLiteDatabase.insert(DATABASE_TABLE, null, contentValues);
    }

    public int delete(String id){
        return sqLiteDatabase.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});

    }
}
