package com.dev.mooohamed.moviesmvp.Services;

import com.dev.mooohamed.moviesmvp.Data.Models.MovieResponse;
import com.dev.mooohamed.moviesmvp.Data.Models.ReviewResponse;
import com.dev.mooohamed.moviesmvp.Data.Models.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    @GET("{type}")
    Call<MovieResponse> getMovies(@Path("type") String type,@Query("api_key") String key);

    @GET("{id}/videos")
    Call<TrailerResponse> getTrailers(@Path("id")int id, @Query("api_key")String key);

    @GET("{id}/reviews")
    Call<ReviewResponse> getReviews(@Path("id") int id, @Query("api_key") String key);
}
