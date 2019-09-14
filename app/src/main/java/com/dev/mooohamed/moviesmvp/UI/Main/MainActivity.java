package com.dev.mooohamed.moviesmvp.UI.Main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.dev.mooohamed.moviesmvp.Data.OnReceiveMoviesListener;
import com.dev.mooohamed.moviesmvp.Data.OnSendTypeListener;
import com.dev.mooohamed.moviesmvp.Data.Prefs;
import com.dev.mooohamed.moviesmvp.UI.CategoryAdapter;
import com.dev.mooohamed.moviesmvp.UI.MovieAdapter;
import com.dev.mooohamed.moviesmvp.Data.Models.Movie;
import com.dev.mooohamed.moviesmvp.R;

import com.dev.mooohamed.moviesmvp.UI.Details.DetailsData;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.ReceiveMoviesViewListener, OnSendTypeListener {

    @BindView(R.id.rv_categories)
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    CategoryAdapter categoryAdapter;
    OnReceiveMoviesListener listener;
    MainContract.SendDataPresenterListener presenterListener;
    public static final String MovieKey = "movie";
    final static String StateKey = "state";
    final static String PosKey = "pos";
    final static String IndexKey = "index";
    Parcelable stateData;
    int index = 0;
    DetailsData detailsData;
    Display display;
    Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new Prefs(getApplicationContext());
        changeLang(prefs.getLanguage());
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(getResources().getString(R.string.home));
        presenterListener = new MainPresenter(this, this);
        detailsData = new DetailsData(this); // for access data from database
        display = getWindowManager().getDefaultDisplay();
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        categoryAdapter = new CategoryAdapter(this,this);
        listener = categoryAdapter;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(categoryAdapter);
    }

    void changeLang(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = this.getResources().getConfiguration();
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        }
        this.getResources().updateConfiguration(configuration, displayMetrics);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        stateData = layoutManager.onSaveInstanceState();
        outState.putParcelable(StateKey, stateData);
        int pos = layoutManager.findFirstCompletelyVisibleItemPosition();
        if (pos == -1) {
            pos = layoutManager.findLastVisibleItemPosition();
        }
        outState.putInt(PosKey, pos);
        outState.putInt(IndexKey, index);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            stateData = savedInstanceState.getParcelable(StateKey);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (stateData != null) {
            layoutManager.onRestoreInstanceState(stateData);
        }
    }

    @Override
    public void OnReceive(List<Movie> movies) {
        listener.onReceive(movies);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.mi_lang:
                if (prefs.getLanguage().equals("en")) {
                    prefs.setLanguage("ar");
                    recreate();
                } else {
                    prefs.setLanguage("en");
                    recreate();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSend(String type) {
        presenterListener.OnSendType(type);
    }
}