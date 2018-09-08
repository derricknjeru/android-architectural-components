package com.derrick.architecturalcomponents.data.network;

import com.derrick.architecturalcomponents.data.database.MovieEntry;
import com.google.gson.annotations.SerializedName;

public class MoviesResponse {
    public MovieEntry[] getmMovies() {
        return mMovies;
    }

    @SerializedName("results")
    private MovieEntry[] mMovies;


}
