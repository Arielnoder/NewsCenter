package com.example.newscenter

import com.example.newscenter.data.remote.NewsDataApi

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module

object AppModule {


    @Singleton
    @Provides
    fun provideNewsDataApi(): NewsDataApi  {
        return Retrofit.Builder()
            .baseUrl("https://newsdata.io/api/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsDataApi::class.java)
    }
}