package com.eve.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eve.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    // Character
    @Query("SELECT * FROM character")
    fun getAllCharacter(): Flow<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(news: List<CharacterEntity>)

    // Other query ...
}