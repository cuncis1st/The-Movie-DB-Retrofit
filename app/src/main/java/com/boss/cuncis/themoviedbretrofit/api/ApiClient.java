package com.boss.cuncis.themoviedbretrofit.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;

    public TheMovieDbApi getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(TheMovieDbApi.class);
    }
}
