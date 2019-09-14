package com.dev.mooohamed.moviesmvp.UI.Details;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.mooohamed.moviesmvp.Data.Models.Movie;
import com.dev.mooohamed.moviesmvp.Data.Models.Review;
import com.dev.mooohamed.moviesmvp.Data.Models.Trailer;
import com.dev.mooohamed.moviesmvp.R;
import com.dev.mooohamed.moviesmvp.Services.Urls;
import com.dev.mooohamed.moviesmvp.UI.Main.MainActivity;
import com.dev.mooohamed.moviesmvp.UI.ReviewAdapter;
import com.dev.mooohamed.moviesmvp.UI.TrailerAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.FavoriteView {

    @BindView(R.id.iv_poster)
    ImageView poster;
    @BindView(R.id.tv_date)
    TextView date;
    @BindView(R.id.tv_rate)
    TextView rate;
    @BindView(R.id.tv_overview)
    TextView overview;
    @BindView(R.id.rv_trailers)
    RecyclerView trailerRecycler;
    @BindView(R.id.rv_reviews)
    RecyclerView reviewRecycler;
    @BindView(R.id.iv_bg)
    ImageView bg;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.trailer_progress)
    ProgressBar trailerProgressBar;
    @BindView(R.id.review_progress)
    ProgressBar reviewProgressBar;
    Movie movie;
    DetailsContract.FavoritePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra(MainActivity.MovieKey)){
            movie = intent.getExtras().getParcelable("movie");
            showDetails(movie);
        }
        presenter = new DetailsPresenter(DetailsActivity.this,this);
        presenter.sendData(movie.getId());
        presenter.sendId(movie.getId());
        fab.setOnClickListener(listener);
        if (presenter.isFavorite(movie.getId())){
            fab.setImageResource(R.drawable.ic_star);
        }else {
            fab.setImageResource(R.drawable.ic_favorite);
        }
    }

    public void showDetails(Movie movie) {
        getSupportActionBar().setTitle(movie.getTitle());
        Picasso.with(this).load(Urls.ImageBase+movie.getBackdrop()).into(bg);
        Picasso.with(this).load(Urls.ImageBase+movie.getPoster()).into(poster);
        date.setText(movie.getDate());
        rate.setText(movie.getRate());
        overview.setText(movie.getOverview());
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (presenter.isFavorite(movie.getId())){
                presenter.removeFavorite(movie);
                Toast.makeText(DetailsActivity.this, movie.getTitle()+getResources().getString(R.string.remove_fav), Toast.LENGTH_SHORT).show();
                fab.setImageResource(R.drawable.ic_favorite);
            }else {
                presenter.addMovie(movie);
                Toast.makeText(DetailsActivity.this, movie.getTitle()+getResources().getString(R.string.add_fav), Toast.LENGTH_SHORT).show();
                fab.setImageResource(R.drawable.ic_star);
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void receiveTrailers(List<Trailer> trailers) {
        trailerProgressBar.setVisibility(View.GONE);
        TrailerAdapter adapter = new TrailerAdapter(this,trailers,movie);
        trailerRecycler.setAdapter(adapter);
        trailerRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void receiveReviews(List<Review> reviews) {
        reviewProgressBar.setVisibility(View.GONE);
        ReviewAdapter adapter = new ReviewAdapter(this,reviews);
        reviewRecycler.setAdapter(adapter);
        reviewRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}