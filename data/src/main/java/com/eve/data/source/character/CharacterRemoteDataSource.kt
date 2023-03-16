package com.eve.data.source.character

import com.eve.data.remote.network.ApiResponse
import com.eve.data.remote.response.CharacterResponse
import com.eve.data.remote.service.CharacterService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val characterService: CharacterService,
) {
    fun getAllCharacter(): Flow<ApiResponse<CharacterResponse>> {
        return flow {
            try {
                val response = characterService.getAllCharacter()
                val data = response.results
                if (data.isNotEmpty()) emit(ApiResponse.Success(response))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}