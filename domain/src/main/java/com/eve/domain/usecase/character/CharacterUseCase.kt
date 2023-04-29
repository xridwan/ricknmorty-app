package com.eve.domain.usecase.character

import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.domain.model.Episode
import kotlinx.coroutines.flow.Flow

interface CharacterUseCase {
    fun getAllCharacter(): Flow<Resource<List<Character>>>
    fun getFilterCharacter(name: String): Flow<Resource<List<Character>>>
    fun getCharacter(id: Int?): Flow<Resource<Character>>
    fun getEpisodeItem(url: String): Flow<Resource<Episode>>
}