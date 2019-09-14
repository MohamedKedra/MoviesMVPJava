package com.dev.mooohamed.moviesmvp.UI.Main;

import android.content.Context;
import android.widget.Toast;

import com.dev.mooohamed.moviesmvp.Data.Models.Movie;
import com.dev.mooohamed.moviesmvp.Data.Models.MovieResponse;
import com.dev.mooohamed.moviesmvp.Services.API;
import com.dev.mooohamed.moviesmvp.Services.Urls;

import java.util.List;

import butterknife.internal.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPresenter implements MainContract.SendDataPresenterListener {

    MainContract.ReceiveMoviesViewListener viewListener;
    MainData mainData;
    Context context;

    public MainPresenter(MainContract.ReceiveMoviesViewListener viewListener, Context context){
        this.viewListener = viewListener;
        this.context = context;
    }

    @Override
    public void OnSendType(String type) {
        mainData = new MainData();
        mainData.OnReceive(type,viewListener,context);
    }

    @Override
    public void OnSendMovies(List<Movie> movies) {
        viewListener.OnReceive(movies);
    }
}