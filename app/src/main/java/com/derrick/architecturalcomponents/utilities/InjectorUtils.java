package com.derrick.architecturalcomponents.utilities;

import android.content.Context;

import com.derrick.architecturalcomponents.AppExecutors;
import com.derrick.architecturalcomponents.data.MovieRepository;
import com.derrick.architecturalcomponents.data.database.MovieDatabase;
import com.derrick.architecturalcomponents.data.network.MovieNetworkDataSource;
import com.derrick.architecturalcomponents.ui.main.MainViewModelFactory;

public class InjectorUtils {

    public static MovieNetworkDataSource provideNetworkDataSource(Context context) {
        // This call to provide repository is necessary if the app starts from a service - in this
        // case the repository will not exist unless it is specifically created.
        provideRepository(context.getApplicationContext());
        return MovieNetworkDataSource.getsInstance(context.getApplicationContext());
    }

    public static MovieRepository provideRepository(Context context) {

        MovieDatabase database = MovieDatabase.getInstance(context.getApplicationContext());

        AppExecutors executors = AppExecutors.getInstance();

        MovieNetworkDataSource networkDataSource =
                MovieNetworkDataSource.getsInstance(context.getApplicationContext());

        return MovieRepository.getsInstance(database.movieDao(), networkDataSource, executors);
    }

    public static MainViewModelFactory provideMainActivityViewModelFactory(Context context) {
        MovieRepository repository = provideRepository(context.getApplicationContext());
        return new MainViewModelFactory(repository);
    }
}
