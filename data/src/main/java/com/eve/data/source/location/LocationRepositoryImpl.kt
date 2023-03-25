package com.eve.data.source.location

import com.eve.data.NetworkBoundResource
import com.eve.data.NetworkResource
import com.eve.data.local.entity.LocationEntity
import com.eve.data.remote.network.ApiResponse
import com.eve.data.remote.response.LocationItem
import com.eve.data.remote.response.LocationResponse
import com.eve.domain.Resource
import com.eve.domain.model.Location
import com.eve.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val localDataSource: LocationLocalDataSource,
    private val remoteDataSource: LocationRemoteDataSource,
) : LocationRepository {

    override fun getAllLocation(): Flow<Resource<List<Location>>> =
        object : NetworkBoundResource<List<Location>, LocationResponse>() {
            override fun loadFromDB(): Flow<List<Location>> {
                return localDataSource.getAllLocation().map {
                    LocationEntity.transformToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Location>?): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<LocationResponse>> {
                return remoteDataSource.getAllLocation()
            }

            override suspend fun saveCallResult(data: LocationResponse) {
                val dataList = LocationItem.transformToEntities(data)
                localDataSource.insertLocation(dataList)
            }
        }.asFlow()

    override fun getLocation(id: Int): Flow<Resource<Location>> =
        object : NetworkResource<Location, LocationItem>() {
            override suspend fun callRequest(): Flow<ApiResponse<LocationItem>> {
                return remoteDataSource.getLocation(id)
            }

            override suspend fun resultResponse(data: LocationItem): Location {
                return LocationItem.transformDetailToDomain(data)
            }

            override fun shouldFetch(data: Location?): Boolean {
                return false
            }
        }.asFlow()
}