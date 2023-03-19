package com.eve.data.di

import com.eve.data.source.character.CharacterLocalDataSource
import com.eve.data.source.character.CharacterRemoteDataSource
import com.eve.data.source.character.CharacterRepositoryImpl
import com.eve.data.source.episode.EpisodeLocalDataSource
import com.eve.data.source.episode.EpisodeRemoteDataSource
import com.eve.data.source.episode.EpisodeRepositoryImpl
import com.eve.data.source.location.LocationLocalDataSource
import com.eve.data.source.location.LocationRemoteDataSource
import com.eve.data.source.location.LocationRepositoryImpl
import com.eve.domain.repository.CharacterRepository
import com.eve.domain.repository.EpisodeRepository
import com.eve.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    // Character
    @Provides
    @Singleton
    fun provideCharacterRepository(
        characterLocalDataSource: CharacterLocalDataSource,
        characterRemoteDataSource: CharacterRemoteDataSource,
    ): CharacterRepository {
        return CharacterRepositoryImpl(
            localDataSource = characterLocalDataSource,
            remoteDataSource = characterRemoteDataSource
        )
    }

    /*  Episode Module  */
    @Provides
    @Singleton
    fun provideEpisodeRepository(
        episodeLocalDataSource: EpisodeLocalDataSource,
        episodeRemoteDataSource: EpisodeRemoteDataSource,
    ): EpisodeRepository {
        return EpisodeRepositoryImpl(
            localDataSource = episodeLocalDataSource,
            remoteDataSource = episodeRemoteDataSource
        )
    }

    // Location
    @Provides
    @Singleton
    fun provideLocationRepository(
        locationLocalDataSource: LocationLocalDataSource,
        locationRemoteDataSource: LocationRemoteDataSource,
    ): LocationRepository {
        return LocationRepositoryImpl(
            localDataSource = locationLocalDataSource,
            remoteDataSource = locationRemoteDataSource
        )
    }

    // Other repository module ...
}