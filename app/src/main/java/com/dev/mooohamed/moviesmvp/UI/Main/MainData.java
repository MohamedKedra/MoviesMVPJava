package com.dev.mooohamed.moviesmvp.UI.Main;

import android.content.Context;
import android.widget.Toast;

import com.dev.mooohamed.moviesmvp.Data.Models.Movie;
import com.dev.mooohamed.moviesmvp.Data.Models.MovieResponse;
import com.dev.mooohamed.moviesmvp.Services.API;
import com.dev.mooohamed.moviesmvp.Services.Urls;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainData implements MainContract.ReceiveTypeDataListener {

    Retrofit retrofit;
    API api;
    Call<MovieResponse> call;
    MainContract.SendDataPresenterListener presenterListener;
    List<Movie> movies;
    Context context;

    @Override
    public void OnReceive(String type, MainContract.ReceiveMoviesViewListener viewListener, Context context) {
        this.context = context;
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.base)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);
        call = api.getMovies(type, Urls.key);
        call.enqueue(callback);
        presenterListener = new MainPresenter(viewListener, context);
    }

    Callback<MovieResponse> callback = new Callback<MovieResponse>() {
        @Override
        public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
            if (response.isSuccessful()) {
                presenterListener.OnSendMovies(response.body().getResults());
            } else {
                System.out.println(response.message());
            }
        }

        @Override
        public void onFailure(Call<MovieResponse> call, Throwable t) {
            System.out.println(t.getMessage());
        }
    };
}