package com.eve.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eve.domain.model.Episode
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "episode")
data class EpisodeEntity(
    @PrimaryKey
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("air_date") val air_date: String,
    @ColumnInfo("episode") val episode: String,
    @ColumnInfo("url") val url: String,
    @ColumnInfo("created") val created: String,
) : Parcelable {
    companion object {
        fun transformToDomain(input: List<EpisodeEntity>): List<Episode> {
            return input.map {
                Episode(
                    id = it.id,
                    name = it.name,
                    air_date = it.air_date,
                    episode = it.episode,
                    url = it.url,
                    created = it.created,
                    character = emptyList()
                )
            }
        }
    }
}
