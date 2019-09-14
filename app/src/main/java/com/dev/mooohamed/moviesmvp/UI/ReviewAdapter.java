package com.dev.mooohamed.moviesmvp.UI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.mooohamed.moviesmvp.Data.Models.Review;
import com.dev.mooohamed.moviesmvp.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {

    Context context;
    List<Review> reviews;

    public ReviewAdapter(Context context, List<Review> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ReviewHolder(LayoutInflater.from(context).inflate(R.layout.review_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder reviewHolder, int i) {

        Review review = reviews.get(i);
        reviewHolder.author.setText(review.getAuthor());
        reviewHolder.content.setText(review.getContent());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ReviewHolder extends RecyclerView.ViewHolder {

        TextView author, content;

        public ReviewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.tv_author);
            content = itemView.findViewById(R.id.tv_content);
        }
    }
}
