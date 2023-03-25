package com.eve.data.source.character

import com.eve.data.NetworkBoundResource
import com.eve.data.NetworkResource
import com.eve.data.local.entity.CharacterEntity
import com.eve.data.remote.network.ApiResponse
import com.eve.data.remote.response.CharacterItem
import com.eve.data.remote.response.CharacterResponse
import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val localDataSource: CharacterLocalDataSource,
    private val remoteDataSource: CharacterRemoteDataSource,
) : CharacterRepository {

    override fun getAllCharacter(): Flow<Resource<List<Character>>> =
        object : NetworkBoundResource<List<Character>, CharacterResponse>() {
            override fun loadFromDB(): Flow<List<Character>> {
                return localDataSource.getAllCharacter().map {
                    CharacterEntity.transformToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Character>?): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<CharacterResponse>> {
                return remoteDataSource.getAllCharacter()
            }

            override suspend fun saveCallResult(data: CharacterResponse) {
                val dataList = CharacterItem.transformToEntities(data)
                localDataSource.insertCharacter(dataList)
            }
        }.asFlow()

    override fun getCharacter(id: Int?): Flow<Resource<Character>> =
        object : NetworkResource<Character, CharacterItem>() {
            override suspend fun callRequest(): Flow<ApiResponse<CharacterItem>> {
                return remoteDataSource.getCharacter(id)
            }

            override suspend fun resultResponse(data: CharacterItem): Character {
                return CharacterItem.transformDetailToDomain(data)
            }

            override fun shouldFetch(data: Character?): Boolean {
                return false
            }
        }.asFlow()
}