package com.zonnon.newsapp.data.remote

import com.zonnon.newsapp.data.remote.dto.NewsResponse
import com.zonnon.newsapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ) : NewsResponse
}
