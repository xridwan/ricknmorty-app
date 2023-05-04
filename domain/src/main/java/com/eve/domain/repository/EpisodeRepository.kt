package com.eve.domain.repository

import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.domain.model.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {

    fun getAllEpisode(): Flow<Resource<List<Episode>>>

    fun getFilterEpisode(name: String): Flow<Resource<List<Episode>>>

    fun getEpisode(id: Int?): Flow<Resource<Episode>>

    fun getCharacterItem(url: String?): Flow<Resource<Character>>

}