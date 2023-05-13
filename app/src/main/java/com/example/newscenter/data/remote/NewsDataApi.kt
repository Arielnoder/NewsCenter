package com.example.newscenter.data.remote

import com.example.newscenter.data.remote.responses.Articles
import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface NewsDataApi {
    @GET("/articles.json")
    suspend fun getNewsList(
// get something from the api so we know it works




    ) : Articles?




}