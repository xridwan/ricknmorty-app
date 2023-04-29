package com.eve.data.remote.response

import android.os.Parcelable
import com.eve.data.local.entity.CharacterEntity
import com.eve.data.utils.replaceIfNull
import com.eve.domain.model.Character
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterResponse(
    @field:SerializedName("results")
    val results: List<CharacterItem>,
) : Parcelable

@Parcelize
data class CharacterItem(
    @field:SerializedName("image")
    val image: String?,

    @field:SerializedName("gender")
    val gender: String?,

    @field:SerializedName("species")
    val species: String?,

    @field:SerializedName("created")
    val created: String?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("episode")
    val episode: List<String>,

    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("type")
    val type: String?,

    @field:SerializedName("url")
    val url: String?,

    @field:SerializedName("status")
    val status: String?,
) : Parcelable {
    companion object {
        fun transformToEntities(input: CharacterResponse): List<CharacterEntity> {
            val dataList = ArrayList<CharacterEntity>()
            input.results.map {
                val item = CharacterEntity(
                    id = it.id.replaceIfNull(),
                    image = it.image.replaceIfNull(),
                    gender = it.gender.replaceIfNull(),
                    species = it.species.replaceIfNull(),
                    created = it.created.replaceIfNull(),
                    type = it.type.replaceIfNull(),
                    url = it.url.replaceIfNull(),
                    status = it.status.replaceIfNull(),
                    name = it.name.replaceIfNull(),
                )
                dataList.add(item)
            }
            return dataList
        }

        fun transformDetailToDomain(input: CharacterItem): Character {
            return Character(
                id = input.id.replaceIfNull(),
                image = input.image.replaceIfNull(),
                gender = input.gender.replaceIfNull(),
                species = input.species.replaceIfNull(),
                created = input.created.replaceIfNull(),
                type = input.type.replaceIfNull(),
                url = input.url.replaceIfNull(),
                status = input.status.replaceIfNull(),
                name = input.name.replaceIfNull(),
                episode = input.episode
            )
        }

        fun transformFilterToDomain(input: CharacterResponse): List<Character> {
            val dataList = ArrayList<Character>()
            input.results.map {
                val item = Character(
                    id = it.id.replaceIfNull(),
                    image = it.image.replaceIfNull(),
                    gender = it.gender.replaceIfNull(),
                    species = it.species.replaceIfNull(),
                    created = it.created.replaceIfNull(),
                    type = it.type.replaceIfNull(),
                    url = it.url.replaceIfNull(),
                    status = it.status.replaceIfNull(),
                    name = it.name.replaceIfNull(),
                    episode = it.episode
                )
                dataList.add(item)
            }
            return dataList
        }
    }
}
