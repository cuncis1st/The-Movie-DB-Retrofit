package com.boss.cuncis.themoviedbretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.boss.cuncis.themoviedbretrofit.api.ApiClient;
import com.boss.cuncis.themoviedbretrofit.api.TheMovieDbApi;
import com.boss.cuncis.themoviedbretrofit.model.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("TMDB Popular Movies Today");

        getPopularMovies();
    }

    private void getPopularMovies() {
        TheMovieDbApi theMovieDbApi = new ApiClient().getClient();
        Call<MovieResponse> call = theMovieDbApi.getPopularMovies(this.getString(R.string.api_key));
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse result = response.body();

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }
}
