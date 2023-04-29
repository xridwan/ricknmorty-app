package com.eve.data.remote.service

import com.eve.data.remote.response.CharacterItem
import com.eve.data.remote.response.CharacterResponse
import com.eve.data.remote.response.EpisodeItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface CharacterService {

    @GET("character")
    suspend fun getAllCharacter(): CharacterResponse

    @GET("character/?")
    suspend fun getFilterCharacter(
        @Query("name") name: String
    ): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int?): CharacterItem

    @GET
    suspend fun getEpisodeItem(@Url url: String?): EpisodeItem
}