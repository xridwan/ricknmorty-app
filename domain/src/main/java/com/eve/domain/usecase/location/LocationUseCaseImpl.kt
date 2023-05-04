package com.eve.domain.usecase.location

import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.domain.model.Location
import com.eve.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationUseCaseImpl @Inject constructor(
    private val locationRepository: LocationRepository,
) : LocationUseCase {
    override fun getAllLocation(): Flow<Resource<List<Location>>> {
        return locationRepository.getAllLocation()
    }

    override fun getFilterLocation(name: String): Flow<Resource<List<Location>>> {
        return locationRepository.getFilterLocation(name)
    }

    override fun getLocation(id: Int?): Flow<Resource<Location>> {
        return locationRepository.getLocation(id)
    }

    override fun getCharacterItem(url: String?): Flow<Resource<Character>> {
        return locationRepository.getCharacterItem(url)
    }
}