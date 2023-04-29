package com.eve.data.source.character

import com.eve.data.remote.network.ApiResponse
import com.eve.data.remote.response.CharacterItem
import com.eve.data.remote.response.CharacterResponse
import com.eve.data.remote.response.EpisodeItem
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

    fun getFilterCharacter(name: String): Flow<ApiResponse<CharacterResponse>> {
        return flow {
            try {
                val response = characterService.getFilterCharacter(name)
                val data = response.results
                if (data.isNotEmpty()) emit(ApiResponse.Success(response))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getCharacter(id: Int?): Flow<ApiResponse<CharacterItem>> {
        return flow {
            try {
                val response = characterService.getCharacter(id)
                if (response.id != null) emit(ApiResponse.Success(response))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getEpisodeItem(url: String?): Flow<ApiResponse<EpisodeItem>> {
        return flow {
            try {
                val response = characterService.getEpisodeItem(url)
                if (response.id != null) emit(ApiResponse.Success(response))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}