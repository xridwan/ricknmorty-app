package com.eve.data.source.episode

import com.eve.data.local.entity.EpisodeEntity
import com.eve.data.local.room.EpisodeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeLocalDataSource @Inject constructor(
    private val episodeDao: EpisodeDao,
) {

    fun getAllEpisode(): Flow<List<EpisodeEntity>> = episodeDao.getAllEpisode()

    suspend fun insertEpisode(dataList: List<EpisodeEntity>) = episodeDao.insertEpisode(dataList)

}