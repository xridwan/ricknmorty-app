package com.eve.domain.usecase.location

import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.domain.model.Location
import kotlinx.coroutines.flow.Flow


interface LocationUseCase {
    fun getAllLocation(): Flow<Resource<List<Location>>>
    fun getLocation(id: Int): Flow<Resource<Location>>
}