package com.derrick.architecturalcomponents.ui.main;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.derrick.architecturalcomponents.data.MovieRepository;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MovieRepository mMovieRepository;

    public MainViewModelFactory(MovieRepository mMovieRepository) {
        this.mMovieRepository = mMovieRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieViewModel(mMovieRepository);
    }
}
