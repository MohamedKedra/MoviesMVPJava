package com.dev.mooohamed.moviesmvp.UI.Details;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.dev.mooohamed.moviesmvp.Data.Models.Movie;
import com.dev.mooohamed.moviesmvp.Data.Prefs;

import java.util.ArrayList;
import java.util.List;

public class DetailsData {

    List<Movie> movies;
    DetailsContract.FavoriteView favoriteView;
    Context context;
    Prefs prefs;

    public DetailsData(Context context){
        this.context = context;
        prefs = new Prefs(context);
    }

    public List<Movie> getAllMovies() {
        return prefs.getAllMovies();
    }

    public void addNewMovie(Movie movie){
        if (getAllMovies() == null || getAllMovies().isEmpty()){
            movies = new ArrayList<>();
        }else {
            movies = getAllMovies();
        }
        movies.add(movie);
        prefs.setMovies(movies);
    }

    public void removeMovie(int movieId){
        for (int i = 0; i < getAllMovies().size(); i++) {
            if (movieId == getAllMovies().get(i).getId()){
                getAllMovies().remove(getAllMovies().get(i));
                return;
            }
        }
    }

    public boolean isFavorite(int movieId){
        if (getAllMovies() != null){
            for (int i = 0; i < getAllMovies().size(); i++) {
                if (movieId == getAllMovies().get(i).getId()){
                    return true;
                }
            }
        }
        return false;
    }
}