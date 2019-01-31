package com.boss.cuncis.themoviedbretrofit;

import android.content.res.Configuration;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.boss.cuncis.themoviedbretrofit.adapter.MovieAdapter;
import com.boss.cuncis.themoviedbretrofit.api.ApiClient;
import com.boss.cuncis.themoviedbretrofit.api.TheMovieDbApi;
import com.boss.cuncis.themoviedbretrofit.model.Movie;
import com.boss.cuncis.themoviedbretrofit.model.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("TMDB Popular Movies Today");

        getPopularMovies();

        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getPopularMovies() {
        TheMovieDbApi theMovieDbApi = new ApiClient().getClient();
        Call<MovieResponse> call = theMovieDbApi.getPopularMovies(this.getString(R.string.api_key));
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse result = response.body();

                if (response.isSuccessful()) {
                    movies = result.getResults();
                    onShowRecyclerView();
                } else {
                    Toast.makeText(MainActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onShowRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MovieAdapter(movies, this);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
