package com.eve.data.di

import com.eve.data.remote.service.CharacterService
import com.eve.data.remote.service.EpisodeService
import com.eve.data.source.character.CharacterRemoteDataSource
import com.eve.data.source.episode.EpisodeRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    // Character
    @Provides
    @Singleton
    fun provideCharacterDataSource(
        characterService: CharacterService,
    ): CharacterRemoteDataSource {
        return CharacterRemoteDataSource(characterService)
    }

    /*  Episode */
    @Provides
    @Singleton
    fun provideEpisodeDataSource(
        episodeService: EpisodeService,
    ): EpisodeRemoteDataSource {
        return EpisodeRemoteDataSource(episodeService)
    }

    // Other source module ...
}