package com.eve.domain.di

import com.eve.domain.repository.CharacterRepository
import com.eve.domain.usecase.character.CharacterInteractor
import com.eve.domain.usecase.character.CharacterUseCase
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
}