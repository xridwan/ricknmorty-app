package com.eve.data.di

import com.eve.domain.repository.CharacterRepository
import com.eve.data.source.character.CharacterLocalDataSource
import com.eve.data.source.character.CharacterRemoteDataSource
import com.eve.data.source.character.CharacterRepositoryImpl
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

    // Other repository module ...
}