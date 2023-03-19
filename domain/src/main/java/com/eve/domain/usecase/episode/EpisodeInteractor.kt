package com.eve.domain.usecase.episode

import com.eve.domain.Resource
import com.eve.domain.model.Episode
import com.eve.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeInteractor @Inject constructor(
    private val episodeRepository: EpisodeRepository,
) : EpisodeUseCase {
    override fun getAllEpisode(): Flow<Resource<List<Episode>>> {
        return episodeRepository.getAllEpisode()
    }

}