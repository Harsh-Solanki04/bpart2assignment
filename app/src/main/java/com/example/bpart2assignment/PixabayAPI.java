package com.example.bpart2assignment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayAPI {
    @GET("/api/")
    Call<ImageSearchAns> search(
            @Query("key") String apiKey,
            @Query("q") String query,
            @Query("per_page") int perPage,
            @Query("page") int page
    );
}