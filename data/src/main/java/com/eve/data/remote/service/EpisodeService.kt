package com.eve.data.remote.service

import com.eve.data.remote.response.EpisodeResponse
import retrofit2.http.GET

interface EpisodeService {

    @GET("episode")
    suspend fun getAllEpisode(): EpisodeResponse
}