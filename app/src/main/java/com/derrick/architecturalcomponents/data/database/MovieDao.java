package com.derrick.architecturalcomponents.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

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

    /**
     * Deletes any movie data older than the given day
     *
     * @param date The date to delete all prior movie from (exclusive)
     */
    @Query("DELETE FROM movies WHERE release_date < :date")
    void deleteOldMovies(Date date);

    /**
     * Delete all the movies
     */
    @Query("DELETE FROM movies")
    void deleteOldMovies();

    /**
     * get all movies
     *
     * @return
     */
    @Query("SELECT * FROM movies")
    LiveData<List<MovieEntry>> getAllMovies();

    @Query("SELECT COUNT(id) FROM movies")
    int getDataCount();
}
