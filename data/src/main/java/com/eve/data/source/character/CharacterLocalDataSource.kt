package com.eve.data.source.character

import com.eve.data.local.entity.CharacterEntity
import com.eve.data.local.room.AppDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterLocalDataSource @Inject constructor(
    private val appDao: AppDao,
) {
    fun getAllCharacter(): Flow<List<CharacterEntity>> = appDao.getAllCharacter()

    suspend fun insertCharacter(dataList: List<CharacterEntity>) = appDao.insertCharacter(dataList)
}