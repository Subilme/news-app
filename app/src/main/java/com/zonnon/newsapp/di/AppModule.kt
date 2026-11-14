package com.zonnon.newsapp.di

import android.app.Application
import com.zonnon.newsapp.data.manager.LocalUserManagerImpl
import com.zonnon.newsapp.data.remote.NewsApi
import com.zonnon.newsapp.data.respositories.NewsRepositoryImpl
import com.zonnon.newsapp.domain.manager.LocalUserManager
import com.zonnon.newsapp.domain.repositories.NewsRepository
import com.zonnon.newsapp.domain.usecases.app_entry.AppEntryUsecases
import com.zonnon.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.zonnon.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.zonnon.newsapp.domain.usecases.news.GetNews
import com.zonnon.newsapp.domain.usecases.news.NewsUsecases
import com.zonnon.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUsecases(
        localUserManager: LocalUserManager
    ) = AppEntryUsecases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideNewRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi = newsApi)

    @Provides
    @Singleton
    fun providesNewUsecases(
        newsRepository: NewsRepository
    ): NewsUsecases {
        return NewsUsecases(
            getNews = GetNews(newsRepository = newsRepository)
        )
    }
}
