package com.derrick.architecturalcomponents.data.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.derrick.architecturalcomponents.data.database.converters.DateConverter;
import com.derrick.architecturalcomponents.data.database.converters.GenreConverter;
import com.derrick.architecturalcomponents.data.database.converters.LanguageConverter;

@Database(entities = {MovieEntry.class}, version = 1)
@TypeConverters({DateConverter.class, GenreConverter.class, LanguageConverter.class})
public abstract class MovieDatabase extends RoomDatabase {

    private static final String LOG_TAG = MovieDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "movie_db";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static MovieDatabase sInstance;

    public static MovieDatabase getInstance(Context context) {
        Log.d(LOG_TAG, "Getting the database");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        MovieDatabase.class, MovieDatabase.DATABASE_NAME).build();
                Log.d(LOG_TAG, "Made new database");
            }
        }
        return sInstance;
    }

    // The associated DAOs for the database
    public abstract MovieDao movieDao();

}
