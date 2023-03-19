package com.eve.domain.repository

import com.eve.domain.Resource
import com.eve.domain.model.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {

    fun getAllEpisode(): Flow<Resource<List<Episode>>>

}