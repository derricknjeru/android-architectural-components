package com.derrick.architecturalcomponents.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.derrick.architecturalcomponents.R;
import com.derrick.architecturalcomponents.data.database.MovieEntry;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    public void setMovieEntryList(List<MovieEntry> movieEntryList) {
        this.movieEntryList = movieEntryList;
        notifyDataSetChanged();
    }

    List<MovieEntry> movieEntryList;

    @NonNull
    @Override

    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        MovieEntry movieEntry = movieEntryList.get(position);
        if (movieEntry != null) {
            holder.txtMovieTitle.setText(movieEntry.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return movieEntryList == null ? 0 : movieEntryList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView txtMovieTitle;

        public MainViewHolder(@NonNull View v) {
            super(v);
            txtMovieTitle = v.findViewById(R.id.movie_title);
        }
    }
}
