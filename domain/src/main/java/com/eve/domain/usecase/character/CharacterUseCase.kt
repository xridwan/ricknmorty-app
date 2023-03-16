package com.eve.domain.usecase.character

import com.eve.domain.Resource
import com.eve.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterUseCase {
    fun getAllCharacter(): Flow<Resource<List<Character>>>
}