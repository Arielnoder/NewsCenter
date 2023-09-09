package com.example.newscenter.data.remote
// use the response from https://makoapi.herokuapp.com/articles/all to populate the list Articles
// use the response from https://makoapi.herokuapp.com/articles/all to populate the list Articles
// use the response from https://makoapi.herokuapp.com/articles/all to populate the list Articles
// use the response from https://makoapi.herokuapp.com/articles/all to populate the list Articles
// use the response from https://makoapi.herokuapp.com/articles/all to populate the list Articles



import com.example.newscenter.data.remote.responses.Articles
import retrofit2.http.GET
import retrofit2.http.POST

interface NewsDataApi {

    @POST("articles/saveAll")
    suspend fun saveArticles(): List<Articles>

    @GET("articles/all")
    suspend fun getArticles(): List<Articles>
}