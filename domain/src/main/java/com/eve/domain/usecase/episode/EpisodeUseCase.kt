package com.eve.domain.usecase.episode

import com.eve.domain.Resource
import com.eve.domain.model.Episode
import kotlinx.coroutines.flow.Flow


interface EpisodeUseCase {

    fun getAllEpisode(): Flow<Resource<List<Episode>>>
}