package com.example.charl.myproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://gateway.marvel.com:443";

    @GET("/v1/public/characters?ts=12345&apikey=f6bcacdee12e77de4e40ee83bedfb5e6&hash=cbd4889ef8046745c56b5f240ea4a5d0")
    Call<List<Anime>> getAnime();

}
