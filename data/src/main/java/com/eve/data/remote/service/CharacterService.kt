package com.eve.data.remote.service

import com.eve.data.remote.response.CharacterResponse
import retrofit2.http.GET

interface CharacterService {

    @GET("character")
    suspend fun getAllCharacter(): CharacterResponse
}