package com.derrick.architecturalcomponents.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.derrick.architecturalcomponents.data.database.MovieEntry;
import com.derrick.architecturalcomponents.ui.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieNetworkDataSource {
    private static String LOG_TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "";
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
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response != null && response.body().getmMovies().length != 0) {

                    Log.d(LOG_TAG, "@Movie Number of movies received: " + response.body().getmMovies().length);

                    mMovies.postValue(response.body().getmMovies());
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(LOG_TAG, t.toString());
            }
        });
    }
}
