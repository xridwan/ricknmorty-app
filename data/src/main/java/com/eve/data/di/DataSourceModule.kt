package com.eve.data.di

import com.eve.data.remote.service.CharacterService
import com.eve.data.source.character.CharacterRemoteDataSource
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

    // Other source module ...
}