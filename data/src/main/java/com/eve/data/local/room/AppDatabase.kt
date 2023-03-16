package com.eve.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eve.data.local.entity.CharacterEntity

@Database(
    entities = [
        CharacterEntity::class
        // Other entity ...
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}