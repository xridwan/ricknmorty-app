package com.eve.data.remote.service

import com.eve.data.remote.response.CharacterItem
import com.eve.data.remote.response.LocationItem
import com.eve.data.remote.response.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface LocationService {

    @GET("location")
    suspend fun getAllLocation(): LocationResponse

    @GET("location/?")
    suspend fun getFilterLocation(
        @Query("name") name: String
    ): LocationResponse

    @GET("location/{id}")
    suspend fun getLocation(@Path("id") id: Int?): LocationItem

    @GET
    suspend fun getCharacterItem(@Url url: String?): CharacterItem
}