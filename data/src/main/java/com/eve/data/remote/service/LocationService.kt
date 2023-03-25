package com.eve.data.remote.service

import com.eve.data.remote.response.LocationItem
import com.eve.data.remote.response.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationService {

    @GET("location")
    suspend fun getAllLocation(): LocationResponse

    @GET("location/{id}")
    suspend fun getLocation(@Path("id") id: Int): LocationItem
}