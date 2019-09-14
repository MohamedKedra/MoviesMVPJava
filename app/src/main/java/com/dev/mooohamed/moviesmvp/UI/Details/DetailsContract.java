package com.dev.mooohamed.moviesmvp.UI.Details;

import com.dev.mooohamed.moviesmvp.Data.Models.Movie;
import com.dev.mooohamed.moviesmvp.Data.Models.Review;
import com.dev.mooohamed.moviesmvp.Data.Models.Trailer;

import java.util.List;

public interface DetailsContract {

    interface FavoriteView{

        void receiveTrailers(List<Trailer> trailers);
        void receiveReviews(List<Review> reviews);
    }

    interface FavoritePresenter{

        void addMovie(Movie movie);
        void removeFavorite(Movie movie);
        boolean isFavorite(int movieId);
        void sendData(int id);
        void sendId(int id);
    }
}
