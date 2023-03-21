package com.eve.data.remote.service

import com.eve.data.remote.response.CharacterItem
import com.eve.data.remote.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET("character")
    suspend fun getAllCharacter(): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterItem
}