package com.eve.domain.di

import com.eve.domain.repository.CharacterRepository
import com.eve.domain.repository.EpisodeRepository
import com.eve.domain.repository.LocationRepository
import com.eve.domain.usecase.character.CharacterUseCaseImpl
import com.eve.domain.usecase.character.CharacterUseCase
import com.eve.domain.usecase.episode.EpisodeUseCaseImpl
import com.eve.domain.usecase.episode.EpisodeUseCase
import com.eve.domain.usecase.location.LocationUseCaseImpl
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
    ): CharacterUseCase = CharacterUseCaseImpl(characterRepository)

    @Provides
    @Singleton
    fun provideEpisodeUser(
        episodeRepository: EpisodeRepository,
    ): EpisodeUseCase = EpisodeUseCaseImpl(episodeRepository)

    @Provides
    @Singleton
    fun provideLocationUser(
        locationRepository: LocationRepository,
    ): LocationUseCase = LocationUseCaseImpl(locationRepository)
}