package com.eve.domain.usecase.character

import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterUseCaseImpl @Inject constructor(
    private val characterRepository: CharacterRepository,
) : CharacterUseCase {
    override fun getAllCharacter(): Flow<Resource<List<Character>>> {
        return characterRepository.getAllCharacter()
    }

    override fun getCharacter(id: Int?): Flow<Resource<Character>> {
        return characterRepository.getCharacter(id)
    }
}