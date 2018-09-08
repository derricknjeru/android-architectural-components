package com.derrick.architecturalcomponents.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.Date;

/**
 * {@link Dao} which provides an api for all data operations with the {@link MovieDatabase}
 */
@Dao
public interface MovieDao {
    /**
     * Inserts a list of {@link MovieEntry} into the movies table. If there is a conflicting id
     * or movie_id the movie entry uses the {@link OnConflictStrategy} of replacing the movie
     * data. The required uniqueness of these values is defined in the {@link MovieEntry}.
     *
     * @param movies A list of movies to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(MovieEntry... movies);


    /**
     * Gets the weather for a single day
     *
     * @param date The date you want weather for
     * @return {@link LiveData} with weather for a single day
     */
    @Query("SELECT * FROM movies WHERE release_date = :date")
    LiveData<MovieEntry> getMovieByDate(Date date);

}
