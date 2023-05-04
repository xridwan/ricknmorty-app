package com.eve.domain.repository

import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getAllLocation(): Flow<Resource<List<Location>>>
    fun getFilterLocation(name: String): Flow<Resource<List<Location>>>
    fun getLocation(id: Int?): Flow<Resource<Location>>
    fun getCharacterItem(url: String?): Flow<Resource<Character>>
}