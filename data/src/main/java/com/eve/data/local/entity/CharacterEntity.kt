package com.eve.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eve.domain.model.Character
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("image") val image: String,
    @ColumnInfo("gender") val gender: String,
    @ColumnInfo("species") val species: String,
    @ColumnInfo("created") val created: String,
    @ColumnInfo("type") val type: String,
    @ColumnInfo("url") val url: String,
    @ColumnInfo("status") val status: String,
    @ColumnInfo("name") val name: String,
) : Parcelable {
    companion object {
        fun transformToDomain(input: List<CharacterEntity>): List<Character> {
            return input.map {
                Character(
                    id = it.id,
                    image = it.image,
                    gender = it.gender,
                    species = it.species,
                    created = it.created,
                    type = it.type,
                    url = it.url,
                    status = it.status,
                    name = it.name,
                )
            }
        }
    }
}