package com.derrick.architecturalcomponents.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.derrick.architecturalcomponents.data.MovieRepository;
import com.derrick.architecturalcomponents.data.database.MovieEntry;

import java.util.List;

/**
 * {@link ViewModel} for {@link MainActivity}
 */

public class MovieViewModel extends ViewModel {
    private final LiveData<List<MovieEntry>> mMovie;


    public MovieViewModel(MovieRepository mMovieRepository) {
        this.mMovie = mMovieRepository.getMovies();
    }

    public LiveData<List<MovieEntry>> getmMovie() {
        return mMovie;
    }
}
