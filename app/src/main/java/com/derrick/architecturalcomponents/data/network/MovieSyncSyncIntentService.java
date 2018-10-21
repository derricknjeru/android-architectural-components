package com.derrick.architecturalcomponents.data.network;

import android.app.IntentService;
import android.content.Intent;
import androidx.annotation.Nullable;
import android.util.Log;

import com.derrick.architecturalcomponents.utilities.InjectorUtils;

public class MovieSyncSyncIntentService extends IntentService {

    private static final String LOG_TAG = MovieSyncSyncIntentService.class.getSimpleName();

    public MovieSyncSyncIntentService() {
        super(LOG_TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(LOG_TAG, "@Movie onHandled called");
        MovieNetworkDataSource networkDataSource = InjectorUtils.provideNetworkDataSource(this.getApplicationContext());
        networkDataSource.fetchMovies();
    }
}
