package com.eve.data.remote.response

import android.os.Parcelable
import com.eve.data.local.entity.CharacterEntity
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
    val episode: List<String?>,

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
                    id = it.id ?: 0,
                    image = it.image ?: "",
                    gender = it.gender ?: "",
                    species = it.species ?: "",
                    created = it.created ?: "",
                    type = it.type ?: "",
                    url = it.url ?: "",
                    status = it.status ?: "",
                    name = it.name ?: "",
                )
                dataList.add(item)
            }
            return dataList
        }
    }
}
