package com.zonnon.newsapp.di

import android.app.Application
import com.zonnon.newsapp.data.manager.LocalUserManagerImpl
import com.zonnon.newsapp.domain.manager.LocalUserManager
import com.zonnon.newsapp.domain.usecases.app_entry.AppEntryUsecases
import com.zonnon.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.zonnon.newsapp.domain.usecases.app_entry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    ) = AppEntryUsecases (
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}
