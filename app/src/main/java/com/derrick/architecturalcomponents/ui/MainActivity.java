package com.derrick.architecturalcomponents.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.derrick.architecturalcomponents.AppExecutors;
import com.derrick.architecturalcomponents.R;
import com.derrick.architecturalcomponents.data.database.MovieDatabase;
import com.derrick.architecturalcomponents.utilities.InjectorUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private MovieViewModel mViewModel;
    private RecyclerView mRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        mRecycleView = findViewById(R.id.main_list);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));

        final MainAdapter adapter = new MainAdapter();
        mRecycleView.setAdapter(adapter);


        MainViewModelFactory factory = InjectorUtils.provideMainActivityViewModelFactory(this);

        mViewModel = ViewModelProviders.of(this, factory).get(MovieViewModel.class);

        mViewModel.getmMovie().observe(this, movieEntries -> {

            Log.d(LOG_TAG, "@Movie movieEntries::" + movieEntries.size());
            adapter.setMovieEntryList(movieEntries);

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
