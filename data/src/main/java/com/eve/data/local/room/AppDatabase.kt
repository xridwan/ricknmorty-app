package com.eve.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eve.data.local.entity.CharacterEntity
import com.eve.data.local.entity.EpisodeEntity
import com.eve.data.local.entity.LocationEntity

@Database(
    entities = [
        CharacterEntity::class,
        EpisodeEntity::class,
        LocationEntity::class
        // Other entity ...
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun locationDao(): LocationDao
}