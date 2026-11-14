package com.zonnon.newsapp.di

import android.app.Application
import androidx.room.Room
import com.zonnon.newsapp.data.local.NewsDao
import com.zonnon.newsapp.data.local.NewsDatabase
import com.zonnon.newsapp.data.local.NewsTypeConverter
import com.zonnon.newsapp.data.manager.LocalUserManagerImpl
import com.zonnon.newsapp.data.remote.NewsApi
import com.zonnon.newsapp.data.respositories.NewsRepositoryImpl
import com.zonnon.newsapp.domain.manager.LocalUserManager
import com.zonnon.newsapp.domain.repositories.NewsRepository
import com.zonnon.newsapp.domain.usecases.app_entry.AppEntryUsecases
import com.zonnon.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.zonnon.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.zonnon.newsapp.domain.usecases.news.DeleteArticle
import com.zonnon.newsapp.domain.usecases.news.GetNews
import com.zonnon.newsapp.domain.usecases.news.NewsUsecases
import com.zonnon.newsapp.domain.usecases.news.SearchNews
import com.zonnon.newsapp.domain.usecases.news.SelectArticle
import com.zonnon.newsapp.domain.usecases.news.SelectArticles
import com.zonnon.newsapp.domain.usecases.news.UpsertArticle
import com.zonnon.newsapp.util.Constants.BASE_URL
import com.zonnon.newsapp.util.Constants.NEWS_DATABASE_NAME
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
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi = newsApi, newsDao = newsDao)

    @Provides
    @Singleton
    fun providesNewUsecases(
        newsRepository: NewsRepository,
    ): NewsUsecases {
        return NewsUsecases(
            getNews = GetNews(newsRepository = newsRepository),
            searchNews = SearchNews(newsRepository = newsRepository),
            upsertArticle = UpsertArticle(newsRepository = newsRepository),
            deleteArticle = DeleteArticle(newsRepository = newsRepository),
            selectArticles = SelectArticles(newsRepository = newsRepository),
            selectArticle = SelectArticle(newsRepository = newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}
