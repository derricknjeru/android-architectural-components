package com.derrick.architecturalcomponents.data.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Room;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Defines the schema of a table in {@link Room} for a single MovieEntry
 * The movie_id is used as an {@link Index} so that its uniqueness can be ensured. Indexes
 * also allow for fast lookup for the column.
 */
@Entity(tableName = "movies", indices = {@Index(value = "movie_id", unique = true)})
public class MovieEntry {
    /**
     * Ensure id is the primary key and is auto generated by{@link Room}
     */
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;
    @SerializedName("vote_count")
    private int vote_count;
    @SerializedName("movie_id")
    private int movie_id;
    @SerializedName("video")
    private boolean video = false;
    @SerializedName("vote_average")
    private double vote_average;
    @SerializedName("title")
    private String title;
    @SerializedName("popularity")
    private double popularity;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("original_languages")
    private List<String> original_languages;
    @SerializedName("original_title")
    private String original_title;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("adult")
    private boolean adult = false;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private Date release_date;
    @SerializedName("genre_ids")
    private List<Integer> genre_ids = null;

    public MovieEntry(int id, int vote_count, int movie_id, boolean video, double vote_average, String title, double popularity, String poster_path, List<String> original_languages, String original_title, String backdrop_path, boolean adult, String overview, Date release_date, List<Integer> genre_ids) {
        this.id = id;
        this.vote_count = vote_count;
        this.movie_id = movie_id;
        this.video = video;
        this.vote_average = vote_average;
        this.title = title;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.original_languages = original_languages;
        this.original_title = original_title;
        this.backdrop_path = backdrop_path;
        this.adult = adult;
        this.overview = overview;
        this.release_date = release_date;
        this.genre_ids = genre_ids;
    }

    /**
     * This constructor is used to by api to pass data.
     * Am using ignore because it is not needed by room and also Room requires only one constructor
     *
     * @param vote_count
     * @param movie_id
     * @param video
     * @param vote_average
     * @param title
     * @param popularity
     * @param poster_path
     * @param original_languages
     * @param original_title
     * @param backdrop_path
     * @param adult
     * @param overview
     * @param release_date
     */
    @Ignore
    public MovieEntry(int vote_count, int movie_id, boolean video, double vote_average, String title, double popularity, String poster_path, List<String> original_languages, String original_title, String backdrop_path, boolean adult, String overview, Date release_date, List<Integer> genre_ids) {
        this.vote_count = vote_count;
        this.movie_id = movie_id;
        this.video = video;
        this.vote_average = vote_average;
        this.title = title;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.original_languages = original_languages;
        this.original_title = original_title;
        this.backdrop_path = backdrop_path;
        this.adult = adult;
        this.overview = overview;
        this.release_date = release_date;
        this.genre_ids = genre_ids;
    }

    public int getId() {
        return id;
    }

    public int getVote_count() {
        return vote_count;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getTitle() {
        return title;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }


    public String getOriginal_title() {
        return original_title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public List<String> getOriginal_languages() {
        return original_languages;
    }
}
