package com.dev.mooohamed.moviesmvp.UI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dev.mooohamed.moviesmvp.Data.Models.Movie;
import com.dev.mooohamed.moviesmvp.Data.OnReceiveMoviesListener;
import com.dev.mooohamed.moviesmvp.Data.OnSendTypeListener;
import com.dev.mooohamed.moviesmvp.R;
import com.dev.mooohamed.moviesmvp.Services.Urls;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> implements OnReceiveMoviesListener {

    Context context;
    OnSendTypeListener listener;
    CategoryHolder categoryHolder;
    String[] keys;

    public CategoryAdapter(Context context, OnSendTypeListener listener) {
        this.context = context;
        this.listener = listener;
        keys = new String[]{
                context.getResources().getString(R.string.popular),
                context.getResources().getString(R.string.rated),
                context.getResources().getString(R.string.playing),
                context.getResources().getString(R.string.upcoming),
                context.getResources().getString(R.string.latest)
        };
    }

    String[] values = new String[]{
            Urls.Popular, Urls.Rated, Urls.Playing, Urls.Upcoming, Urls.Latest
    };

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item, viewGroup, false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryHolder categoryHolder, final int i) {
        this.categoryHolder = categoryHolder;
        categoryHolder.title.setText(keys[i]);
    }

    @Override
    public int getItemCount() {
        return keys.length;
    }

    @Override
    public void onReceive(List<Movie> movies) {

        categoryHolder.progressBar.setVisibility(View.GONE);
        if (movies == null) {
            categoryHolder.noMovies.setVisibility(View.VISIBLE);
        } else {
            categoryHolder.noMovies.setVisibility(View.GONE);
            MovieAdapter adapter = new MovieAdapter(context, movies);
            categoryHolder.recyclerView.setVisibility(View.VISIBLE);
            categoryHolder.recyclerView.setAdapter(adapter);
            categoryHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        }
    }

    public class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout header;
        TextView title, noMovies;
        ImageView icon;
        RecyclerView recyclerView;
        CoordinatorLayout body;
        ProgressBar progressBar;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header);
            title = itemView.findViewById(R.id.tv_title);
            noMovies = itemView.findViewById(R.id.tv_noMovies);
            icon = itemView.findViewById(R.id.iv_icon);
            body = itemView.findViewById(R.id.cl_body);
            progressBar = itemView.findViewById(R.id.progress_bar);
            recyclerView = itemView.findViewById(R.id.rv_movies);
            header.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            System.out.println(getAdapterPosition());
            if (body.getVisibility() == View.VISIBLE) {
                icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_arrow_down));
                body.setVisibility(View.GONE);
            } else {
                body.setVisibility(View.VISIBLE);
                icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_arrow_up));
                progressBar.setVisibility(View.VISIBLE);
                listener.onSend(values[getAdapterPosition()]);
                notifyItemChanged(getAdapterPosition());
            }
        }
    }
}