package com.eve.data.source.location

import com.eve.data.local.entity.LocationEntity
import com.eve.data.local.room.LocationDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationLocalDataSource @Inject constructor(
    private val locationDao: LocationDao,
) {

    fun getAllLocation(): Flow<List<LocationEntity>> = locationDao.getAllLocation()

    suspend fun insertLocation(dataList: List<LocationEntity>) =
        locationDao.insertLocation(dataList)

}