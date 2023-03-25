package com.eve.data.source.location

import com.eve.data.remote.network.ApiResponse
import com.eve.data.remote.response.LocationItem
import com.eve.data.remote.response.LocationResponse
import com.eve.data.remote.service.LocationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocationRemoteDataSource @Inject constructor(
    private val locationService: LocationService,
) {

    fun getAllLocation(): Flow<ApiResponse<LocationResponse>> {
        return flow {
            try {
                val response = locationService.getAllLocation()
                val data = response.results
                if (data.isNotEmpty()) emit(ApiResponse.Success(response))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getLocation(id: Int): Flow<ApiResponse<LocationItem>> {
        return flow {
            try {
                val response = locationService.getLocation(id)
                if (response.id != null) emit(ApiResponse.Success(response))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}