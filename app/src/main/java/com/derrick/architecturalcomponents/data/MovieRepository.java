package com.derrick.architecturalcomponents.data;

import androidx.lifecycle.LiveData;
import android.util.Log;

import com.derrick.architecturalcomponents.AppExecutors;
import com.derrick.architecturalcomponents.data.database.MovieDao;
import com.derrick.architecturalcomponents.data.database.MovieEntry;
import com.derrick.architecturalcomponents.data.network.MovieNetworkDataSource;

import java.util.Date;
import java.util.List;

public class MovieRepository {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static final String LOG_TAG = MovieRepository.class.getSimpleName();
    private static MovieRepository sInstance;
    private final MovieDao mMovieDao;
    private final MovieNetworkDataSource mMovieNetworkDataSource;
    private final AppExecutors mExecutors;
    private boolean mInitialized = false;

    private MovieRepository(final MovieDao movieDao, MovieNetworkDataSource movieNetworkDataSource, AppExecutors executors) {
        mMovieDao = movieDao;
        mExecutors = executors;
        mMovieNetworkDataSource = movieNetworkDataSource;

        // As long as the repository exists, observe the network LiveData.
        // If that LiveData changes, update the database.

        LiveData<MovieEntry[]> networkData = mMovieNetworkDataSource.getMovies();

        networkData.observeForever(movieEntries -> mExecutors.diskIO().execute(() -> {
            //delete old data
            deleteOldMovies();
            Log.d(LOG_TAG, "@Movie deleted");

            Log.d(LOG_TAG, "@Movie deleted movieEntries"+movieEntries);
            //insert new movie data
             movieDao.bulkInsert(movieEntries);
            Log.d(LOG_TAG, "@Movie saved");

        }));

    }

    /**
     * Deletes all movies data
     */
    private void deleteOldMovies() {
        mMovieDao.deleteOldMovies();
    }

    public synchronized static MovieRepository getsInstance(MovieDao mMovieDao, MovieNetworkDataSource networkDataSource, AppExecutors executors) {
        if (sInstance == null) {
            //new repository is created
            synchronized (LOCK) {
                sInstance = new MovieRepository(mMovieDao, networkDataSource, executors);
            }

        }
        return sInstance;
    }

    /**
     * Creates periodic sync tasks and checks to see if an immediate sync is required. If an
     * immediate sync is required, this method will take care of making sure that sync occurs.
     */
    private synchronized void initializeData() {

        // Only perform initialization once per app lifetime. If initialization has already been
        // performed, we have nothing to do in this method.
        Log.d(LOG_TAG, "@Movie " + mInitialized);

        if (mInitialized) return;
        mInitialized = true;

        // This method call triggers Sunshine to create its task to synchronize movie data
        // periodically.
        mMovieNetworkDataSource.scheduleRecurringFetchMovieSync();

        Log.d(LOG_TAG, "@Movie after scheduleRecurringFetchMovieSync");

        mExecutors.diskIO().execute(() -> {
            Log.d(LOG_TAG, "@Movie start fetching");
            if (isFetchNeeded()) {
                Log.d(LOG_TAG, "@Movie there");
                startFetchWeatherService();
            }
        });
    }

    private void startFetchWeatherService() {
        mMovieNetworkDataSource.startFetchWeatherService();
    }

    /**
     * check whether we should refresh data or not
     *
     * @return
     */
    private boolean isFetchNeeded() {
        return true;
    }


    public LiveData<MovieEntry> getMoviesByDate(Date date) {
        initializeData();
        return mMovieDao.getMovieByDate(date);
    }

    public LiveData<List<MovieEntry>> getMovies() {
        initializeData();
        return mMovieDao.getAllMovies();
    }


}
