package com.eve.data.source.episode

import com.eve.data.NetworkBoundResource
import com.eve.data.local.entity.EpisodeEntity
import com.eve.data.remote.network.ApiResponse
import com.eve.data.remote.response.EpisodeItem
import com.eve.data.remote.response.EpisodeResponse
import com.eve.domain.Resource
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
}