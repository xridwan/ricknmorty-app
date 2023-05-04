package com.eve.data.remote.service

import com.eve.data.remote.response.CharacterItem
import com.eve.data.remote.response.CharacterResponse
import com.eve.data.remote.response.EpisodeItem
import com.eve.data.remote.response.EpisodeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface EpisodeService {

    @GET("episode")
    suspend fun getAllEpisode(): EpisodeResponse

    @GET("episode/?")
    suspend fun getFilterEpisode(
        @Query("name") name: String
    ): EpisodeResponse

    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") id: Int?): EpisodeItem

    @GET
    suspend fun getCharacterItem(@Url url: String?): CharacterItem
}