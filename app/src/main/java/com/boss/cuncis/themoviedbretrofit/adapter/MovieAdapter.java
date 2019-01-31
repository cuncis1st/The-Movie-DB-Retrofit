package com.boss.cuncis.themoviedbretrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.boss.cuncis.themoviedbretrofit.DetailMovieActivity;
import com.boss.cuncis.themoviedbretrofit.R;
import com.boss.cuncis.themoviedbretrofit.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    List<Movie> movies;
    Context context;

    public MovieAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_data_movie, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int i) {
        holder.tvTitle.setText(movies.get(i).getOriginalTitle());
        holder.tvRating.setText(Double.toString(movies.get(i).getVoteAverage()));
        String imgPath = "https://image.tmdb.org/t/p/w500" + movies.get(i).getPosterPath();

        Picasso.get()
                .load(imgPath)
                .placeholder(R.drawable.img_placeholder)
                .into(holder.imgMovie);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvRating;
        ImageView imgMovie;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvRating = itemView.findViewById(R.id.tv_rating);
            imgMovie = itemView.findViewById(R.id.img_movie);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        Movie selecedMovie = movies.get(position);

                        Intent i = new Intent(context, DetailMovieActivity.class);
                        i.putExtra("MOVIE", selecedMovie);
                        context.startActivity(i);
                    }
                }
            });
        }
    }
}
