package com.eve.data.source.episode

import com.eve.data.NetworkBoundResource
import com.eve.data.NetworkResource
import com.eve.data.local.entity.EpisodeEntity
import com.eve.data.remote.network.ApiResponse
import com.eve.data.remote.response.CharacterItem
import com.eve.data.remote.response.EpisodeItem
import com.eve.data.remote.response.EpisodeResponse
import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.domain.model.Episode
import com.eve.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val localDataSource: EpisodeLocalDataSource,
    private val remoteDataSource: EpisodeRemoteDataSource,
) : EpisodeRepository {

    override fun getAllEpisode(): Flow<Resource<List<Episode>>> =
        object : NetworkBoundResource<List<Episode>, EpisodeResponse>() {
            override fun loadFromDB(): Flow<List<Episode>> {
                return localDataSource.getAllEpisode().map {
                    EpisodeEntity.transformToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Episode>?): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<EpisodeResponse>> {
                return remoteDataSource.getAllEpisode()
            }

            override suspend fun saveCallResult(data: EpisodeResponse) {
                val dataList = EpisodeItem.transformToEntities(data)
                localDataSource.insertEpisode(dataList)
            }
        }.asFlow()

    override fun getFilterEpisode(name: String): Flow<Resource<List<Episode>>> =
        object : NetworkResource<List<Episode>, EpisodeResponse>() {
            override suspend fun callRequest(): Flow<ApiResponse<EpisodeResponse>> {
                return remoteDataSource.getFilterEpisode(name)
            }

            override suspend fun resultResponse(data: EpisodeResponse): List<Episode> {
                return EpisodeItem.transformFilterToDomain(data)
            }

            override fun shouldFetch(data: List<Episode>?): Boolean {
                return false
            }
        }.asFlow()


    override fun getEpisode(id: Int?): Flow<Resource<Episode>> =
        object : NetworkResource<Episode, EpisodeItem>() {
            override suspend fun callRequest(): Flow<ApiResponse<EpisodeItem>> {
                return remoteDataSource.getEpisode(id)
            }

            override suspend fun resultResponse(data: EpisodeItem): Episode {
                return EpisodeItem.transformDetailToDomain(data)
            }

            override fun shouldFetch(data: Episode?): Boolean {
                return false
            }
        }.asFlow()

    override fun getCharacterItem(url: String?): Flow<Resource<Character>> =
        object : NetworkResource<Character, CharacterItem>() {
            override suspend fun callRequest(): Flow<ApiResponse<CharacterItem>> {
                return remoteDataSource.getCharacterItem(url)
            }

            override suspend fun resultResponse(data: CharacterItem): Character {
                return CharacterItem.transformDetailToDomain(data)
            }

            override fun shouldFetch(data: Character?): Boolean {
                return false
            }
        }.asFlow()
}

