package com.dev.mooohamed.moviesmvp.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dev.mooohamed.moviesmvp.Data.Models.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class Prefs {

    static final String USER = "user";
    static final String LANG = "language";
    static final String MOVIE = "movie";
    SharedPreferences sharedPreferences;
    Gson gson;

    public Prefs(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        gson = new Gson();
    }

    public void setMovies(List<Movie> movies){

        sharedPreferences.edit().putString(MOVIE,new Gson().toJson(movies));
    }

    public List<Movie> getAllMovies(){
        String data = sharedPreferences.getString(MOVIE,"");
        Type type = new TypeToken<List<Movie>>(){}.getType();
        return new Gson().fromJson(data,type);
    }

    public void setUser(String user){
        sharedPreferences.edit().putString(USER,user).apply();
    }

    public String getUser(){
        return sharedPreferences.getString(USER,"");
    }

    public void clearUser(){

        sharedPreferences.edit().clear().apply();
    }

    public void setLanguage(String language){
        sharedPreferences.edit().putString(LANG,language).apply();
    }

    public String getLanguage(){
        return sharedPreferences.getString(LANG,"");
    }

}
