package com.dev.mooohamed.moviesmvp.Data;

import com.dev.mooohamed.moviesmvp.Data.Models.Movie;
import com.dev.mooohamed.moviesmvp.UI.CategoryAdapter;

import java.util.List;

public interface OnReceiveMoviesListener {

    void onReceive(List<Movie> movies);
}
