package com.eve.data.di

import com.eve.data.source.character.CharacterLocalDataSource
import com.eve.data.source.character.CharacterRemoteDataSource
import com.eve.data.source.character.CharacterRepositoryImpl
import com.eve.data.source.episode.EpisodeLocalDataSource
import com.eve.data.source.episode.EpisodeRemoteDataSource
import com.eve.data.source.episode.EpisodeRepositoryImpl
import com.eve.domain.repository.CharacterRepository
import com.eve.domain.repository.EpisodeRepository
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

    // Other repository module ...
}