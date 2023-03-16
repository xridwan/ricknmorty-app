package com.eve.data.di

import android.app.Application
import androidx.room.Room
import com.eve.data.local.room.AppDao
import com.eve.data.local.room.AppDatabase
import com.eve.data.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application) = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideDao(database: AppDatabase): AppDao {
        return database.appDao()
    }
}