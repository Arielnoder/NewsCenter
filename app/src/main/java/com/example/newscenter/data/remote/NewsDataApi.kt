package com.example.newscenter.data.remote

import com.example.newscenter.data.remote.responses.Article
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsDataApi {
    @GET("news")
    suspend fun getNewsList(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("language") language: String
    ) : Article

    @GET("news")
    suspend fun getNewsQuery(
        @Query("apiKey") apiKey: String,
        @Query("q") query: String
    ) : Article
}