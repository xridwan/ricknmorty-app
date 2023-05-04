package com.eve.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eve.domain.model.Location
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "location")
data class LocationEntity(
    @PrimaryKey
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("created") val created: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("type") val type: String,
    @ColumnInfo("dimension") val dimension: String,
    @ColumnInfo("url") val url: String,
) : Parcelable {
    companion object {
        fun transformToDomain(input: List<LocationEntity>): List<Location> {
            return input.map {
                Location(
                    id = it.id,
                    created = it.created,
                    name = it.name,
                    type = it.type,
                    dimension = it.dimension,
                    url = it.url,
                    residents = emptyList()
                )
            }
        }
    }
}