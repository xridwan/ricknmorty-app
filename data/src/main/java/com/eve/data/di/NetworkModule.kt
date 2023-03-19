package com.eve.data.di

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.eve.data.BuildConfig.BASE_URL
import com.eve.data.remote.service.CharacterService
import com.eve.data.remote.service.EpisodeService
import com.eve.data.remote.service.LocationService
import com.eve.data.utils.Constants.CONNECT_TIMEOUT
import com.eve.data.utils.Constants.READ_TIMEOUT
import com.eve.data.utils.Constants.WRITE_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideChucker(@ApplicationContext context: Context): ChuckerInterceptor =
        ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context))
            .maxContentLength(READ_TIMEOUT)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        chuckerInterceptor: ChuckerInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(if (BuildConfig.DEBUG) chuckerInterceptor else httpLoggingInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    // Character
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CharacterService =
        retrofit.create(CharacterService::class.java)

    /*  Episode */
    @Provides
    @Singleton
    fun provideEpisodeService(retrofit: Retrofit): EpisodeService =
        retrofit.create(EpisodeService::class.java)

    // Location
    @Provides
    @Singleton
    fun provideLocationService(retrofit: Retrofit): LocationService =
        retrofit.create(LocationService::class.java)

    // Other service ...
}