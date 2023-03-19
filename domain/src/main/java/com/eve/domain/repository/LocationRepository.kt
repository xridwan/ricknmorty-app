package com.eve.domain.repository

import com.eve.domain.Resource
import com.eve.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getAllLocation(): Flow<Resource<List<Location>>>
}