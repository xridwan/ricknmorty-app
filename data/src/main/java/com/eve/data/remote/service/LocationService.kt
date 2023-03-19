package com.eve.data.remote.service

import com.eve.data.remote.response.LocationResponse
import retrofit2.http.GET

interface LocationService {

    @GET("location")
    suspend fun getAllLocation(): LocationResponse
}