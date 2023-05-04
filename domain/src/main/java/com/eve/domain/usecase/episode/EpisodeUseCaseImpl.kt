package com.eve.domain.usecase.episode

import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.domain.model.Episode
import com.eve.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeUseCaseImpl @Inject constructor(
    private val episodeRepository: EpisodeRepository,
) : EpisodeUseCase {
    override fun getAllEpisode(): Flow<Resource<List<Episode>>> {
        return episodeRepository.getAllEpisode()
    }

    override fun getFilterEpisode(name: String): Flow<Resource<List<Episode>>> {
        return episodeRepository.getFilterEpisode(name)
    }

    override fun getEpisode(id: Int?): Flow<Resource<Episode>> {
        return episodeRepository.getEpisode(id)
    }

    override fun getCharacterItem(url: String?): Flow<Resource<Character>> {
        return episodeRepository.getCharacterItem(url)
    }

}