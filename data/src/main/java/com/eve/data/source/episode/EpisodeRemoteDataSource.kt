package com.eve.data.source.episode

import com.eve.data.remote.network.ApiResponse
import com.eve.data.remote.response.EpisodeResponse
import com.eve.data.remote.service.EpisodeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EpisodeRemoteDataSource @Inject constructor(
    private val episodeService: EpisodeService,
) {

    fun getAllEpisode(): Flow<ApiResponse<EpisodeResponse>> {
        return flow {
            try {
                val response = episodeService.getAllEpisode()
                val data = response.results
                if (data.isNotEmpty()) emit(ApiResponse.Success(response))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}