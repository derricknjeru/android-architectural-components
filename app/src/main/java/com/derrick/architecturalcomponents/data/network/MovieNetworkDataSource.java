package com.derrick.architecturalcomponents.data.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.derrick.architecturalcomponents.data.database.MovieEntry;
import com.derrick.architecturalcomponents.ui.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieNetworkDataSource {
    private static String LOG_TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "bb7ce524c99f7a42c1f154f0a7b82d50";
    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static MovieNetworkDataSource sInstance;
    private final Context mContext;

    // LiveData storing the latest downloaded weather forecasts
    private final MutableLiveData<MovieEntry[]> mMovies;

    public MovieNetworkDataSource(Context mContext) {
        this.mContext = mContext;
        mMovies = new MutableLiveData<>();
    }


    public static MovieNetworkDataSource getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new MovieNetworkDataSource(context);
            }
        }
        return sInstance;
    }

    public LiveData<MovieEntry[]> getMovies() {
        return mMovies;
    }

    /**
     * get movies
     */
    void fetchMovies() {
        Log.d(LOG_TAG, "@Movie fetchMovies called");

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                convertToLifeData(response);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed

                Log.d(LOG_TAG, "@Movie error fetching " + t.getMessage());
            }
        });


    }

    private void convertToLifeData(Response<MoviesResponse> response) {
        MoviesResponse moviesResponse = new MoviesResponse(response.body().getmMovies());

        Log.d(LOG_TAG, "@Movie moviesResponse::" + moviesResponse);
        Log.d(LOG_TAG, "@Movie moviesResponse size::" + moviesResponse.getmMovies().length);

        mMovies.setValue(moviesResponse.getmMovies());

    }


    public void scheduleRecurringFetchMovieSync() {


    }

    public void startFetchWeatherService() {
        Intent intentToFetch = new Intent(mContext, MovieSyncSyncIntentService.class);
        mContext.startService(intentToFetch);
        Log.d(LOG_TAG, "@Movie Service created");
    }
}
