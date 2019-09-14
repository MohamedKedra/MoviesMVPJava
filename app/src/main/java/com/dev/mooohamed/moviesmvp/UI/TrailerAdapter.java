package com.dev.mooohamed.moviesmvp.UI;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.mooohamed.moviesmvp.Data.Models.Movie;
import com.dev.mooohamed.moviesmvp.Data.Models.Trailer;
import com.dev.mooohamed.moviesmvp.R;
import com.dev.mooohamed.moviesmvp.Services.Urls;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerHolder> {

    List<Trailer> trailers;
    Context context;
    Movie movie;

    public TrailerAdapter(Context context,List<Trailer> trailers,Movie movie){
        this.context = context;
        this.trailers = trailers;
        this.movie = movie;
    }

    @NonNull
    @Override
    public TrailerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TrailerHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trailer_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerHolder trailerHolder, int i) {

        final Trailer trailer = trailers.get(i);
        trailerHolder.name.setText(trailer.getName());
        trailerHolder.site.setText(trailer.getSite());
        trailerHolder.size.setText(trailer.getSize()+"HD");
        if (i % 2 == 0){
            Picasso.with(context).load(Urls.ImageBase+movie.getBackdrop()).into(trailerHolder.poster);
        }else{
            Picasso.with(context).load(Urls.ImageBase+movie.getPoster()).into(trailerHolder.poster);
        }
        trailerHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(Urls.Youtube+trailer.getKey()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    class TrailerHolder extends RecyclerView.ViewHolder{

        ImageView poster;
        TextView name,site,size;

        public TrailerHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.iv_trailer);
            name = itemView.findViewById(R.id.tv_name);
            site = itemView.findViewById(R.id.tv_site);
            size = itemView.findViewById(R.id.tv_size);
        }
    }
}
