package com.eve.domain.di

import com.eve.domain.repository.CharacterRepository
import com.eve.domain.repository.EpisodeRepository
import com.eve.domain.repository.LocationRepository
import com.eve.domain.usecase.character.CharacterInteractor
import com.eve.domain.usecase.character.CharacterUseCase
import com.eve.domain.usecase.episode.EpisodeInteractor
import com.eve.domain.usecase.episode.EpisodeUseCase
import com.eve.domain.usecase.location.LocationInteractor
import com.eve.domain.usecase.location.LocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideCharacterUser(
        characterRepository: CharacterRepository,
    ): CharacterUseCase = CharacterInteractor(characterRepository)

    @Provides
    @Singleton
    fun provideEpisodeUser(
        episodeRepository: EpisodeRepository,
    ): EpisodeUseCase = EpisodeInteractor(episodeRepository)

    @Provides
    @Singleton
    fun provideLocationUser(
        locationRepository: LocationRepository,
    ): LocationUseCase = LocationInteractor(locationRepository)
}