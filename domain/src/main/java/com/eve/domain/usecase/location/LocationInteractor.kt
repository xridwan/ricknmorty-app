package com.eve.domain.usecase.location

import com.eve.domain.Resource
import com.eve.domain.model.Location
import com.eve.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationInteractor @Inject constructor(
    private val locationRepository: LocationRepository,
) : LocationUseCase {
    override fun getAllLocation(): Flow<Resource<List<Location>>> {
        return locationRepository.getAllLocation()
    }
}