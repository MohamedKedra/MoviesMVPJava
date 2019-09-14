package com.dev.mooohamed.moviesmvp.Data.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {

    private int id;
    private String title;
    @SerializedName("release_date")
    private String date;
    private String overview;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("backdrop_path")
    private String backdrop;
    @SerializedName("vote_average")
    private String rate;

    public Movie(){

    }

    protected Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        date = in.readString();
        overview = in.readString();
        poster = in.readString();
        rate = in.readString();
        backdrop = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(overview);
        dest.writeString(poster);
        dest.writeString(rate);
        dest.writeString(backdrop);
    }
}