package com.example.newscenter

import com.example.newscenter.data.remote.NewsDataApi
import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module

object AppModule {



    @Singleton
    @Provides
    fun provideNewsDataApi( ): NewsDataApi  {

        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1


        // implement the interceptor

        return Retrofit.Builder()


            .baseUrl("https://newsdata.io/api/1/")

            .addConverterFactory(GsonConverterFactory.create())

            .client(okhttp3.OkHttpClient.Builder()
                .dispatcher(dispatcher)


                .build())


            .build()
            .create(NewsDataApi::class.java)


    }

}