package com.dev.mooohamed.moviesmvp.UI.Details;

import android.content.Context;

import com.dev.mooohamed.moviesmvp.Data.Models.Movie;
import com.dev.mooohamed.moviesmvp.Data.Models.MovieResponse;
import com.dev.mooohamed.moviesmvp.Data.Models.Review;
import com.dev.mooohamed.moviesmvp.Data.Models.ReviewResponse;
import com.dev.mooohamed.moviesmvp.Data.Models.Trailer;
import com.dev.mooohamed.moviesmvp.Data.Models.TrailerResponse;
import com.dev.mooohamed.moviesmvp.Services.API;
import com.dev.mooohamed.moviesmvp.Services.Urls;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsPresenter implements DetailsContract.FavoritePresenter{

    DetailsData detailsData;
    DetailsContract.FavoriteView favoriteView;
    API api;

    public DetailsPresenter(Context context, DetailsContract.FavoriteView favoriteView){
        detailsData = new DetailsData(context);
        this.favoriteView = favoriteView;
    }

    @Override
    public void addMovie(Movie movie) {
        detailsData.addNewMovie(movie);
    }

    @Override
    public void removeFavorite(Movie movie) {
        detailsData.removeMovie(movie.getId());
    }

    @Override
    public boolean isFavorite(int movieId) {

        return detailsData.isFavorite(movieId);
    }

    @Override
    public void sendData(int id) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.base)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);
        api.getTrailers(id,Urls.key).enqueue(trailersCallback);
    }

    @Override
    public void sendId(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.base)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);
        api.getReviews(id,Urls.key).enqueue(reviewsCallback);
    }

    Callback<TrailerResponse> trailersCallback = new Callback<TrailerResponse>() {
        @Override
        public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
            if (response.isSuccessful()) {
                favoriteView.receiveTrailers(response.body().getTrailers());
            } else {
                System.out.println(response.message());
            }
        }

        @Override
        public void onFailure(Call<TrailerResponse> call, Throwable t) {
            System.out.println(t.getMessage());
        }
    };

    Callback<ReviewResponse> reviewsCallback = new Callback<ReviewResponse>() {
        @Override
        public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
            if (response.isSuccessful()) {
                favoriteView.receiveReviews(response.body().getReviews());
            } else {
                System.out.println(response.message());
            }
        }

        @Override
        public void onFailure(Call<ReviewResponse> call, Throwable t) {
            System.out.println(t.getMessage());
        }
    };
}