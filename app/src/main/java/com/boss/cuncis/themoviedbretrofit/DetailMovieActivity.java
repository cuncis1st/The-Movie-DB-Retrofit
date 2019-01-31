package com.boss.cuncis.themoviedbretrofit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.boss.cuncis.themoviedbretrofit.model.Movie;
import com.squareup.picasso.Picasso;

public class DetailMovieActivity extends AppCompatActivity {
    private Movie movie;
    private ImageView imgMovie;
    private TextView movieTitle, movieSynopsis, movieRating, movieReleaseDate;

    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imgMovie = findViewById(R.id.img_movieLarge);
        movieTitle = findViewById(R.id.tvMovieTitle);
        movieSynopsis = findViewById(R.id.tvPlotsynopsis);
        movieRating = findViewById(R.id.tvMovieRating);
        movieReleaseDate = findViewById(R.id.tvReleaseDate);

        if (getIntent().hasExtra("MOVIE")) {
            movie = getIntent().getParcelableExtra("MOVIE");
            Toast.makeText(this, "" + movie.getOriginalTitle(), Toast.LENGTH_SHORT).show();
            image = movie.getPosterPath();

            String path = "https://image.tmdb.org/t/p/w500" + image;
            Picasso.get().load(path).placeholder(R.drawable.img_placeholder).into(imgMovie);

            getSupportActionBar().setTitle(movie.getTitle());

            movieTitle.setText(movie.getTitle());
            movieSynopsis.setText(movie.getOverview());
            movieRating.setText(Double.toString(movie.getVoteAverage()));
            movieReleaseDate.setText(movie.getReleaseDate());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
