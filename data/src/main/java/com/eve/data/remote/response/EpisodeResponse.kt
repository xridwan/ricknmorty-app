package com.eve.data.remote.response

import android.os.Parcelable
import com.eve.data.local.entity.EpisodeEntity
import com.eve.data.utils.replaceIfNull
import com.eve.domain.model.Episode
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodeResponse(

    @field:SerializedName("results")
    val results: List<EpisodeItem>,

//	@field:SerializedName("info")
//	val info: Info? = null,
) : Parcelable

//data class Info(
//
//	@field:SerializedName("next")
//	val next: String?,
//
//	@field:SerializedName("pages")
//	val pages: Int?,
//
//	@field:SerializedName("prev")
//	val prev: Any?,
//
//	@field:SerializedName("count")
//	val count: Int?,
//)

@Parcelize
data class EpisodeItem(

    @field:SerializedName("air_date")
    val airDate: String?,

    @field:SerializedName("characters")
    val characters: List<String>,

    @field:SerializedName("created")
    val created: String?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("episode")
    val episode: String?,

    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("url")
    val url: String?,
) : Parcelable {
    companion object {
        fun transformToEntities(input: EpisodeResponse): List<EpisodeEntity> {
            val dataList = ArrayList<EpisodeEntity>()
            input.results.map {
                val item = EpisodeEntity(
                    id = it.id.replaceIfNull(),
                    name = it.name.replaceIfNull(),
                    air_date = it.airDate.replaceIfNull(),
                    episode = it.episode.replaceIfNull(),
                    url = it.url.replaceIfNull(),
                    created = it.created.replaceIfNull()
                )
                dataList.add(item)
            }
            return dataList
        }

        fun transformFilterToDomain(input: EpisodeResponse): List<Episode> {
            val dataList = ArrayList<Episode>()
            input.results.map {
                val item = Episode(
                    id = it.id.replaceIfNull(),
                    name = it.name.replaceIfNull(),
                    air_date = it.airDate.replaceIfNull(),
                    episode = it.episode.replaceIfNull(),
                    url = it.episode.replaceIfNull(),
                    created = it.created.replaceIfNull(),
                    character = it.characters
                )
                dataList.add(item)
            }
            return dataList
        }

        fun transformDetailToDomain(input: EpisodeItem): Episode {
            return Episode(
                id = input.id.replaceIfNull(),
                created = input.created.replaceIfNull(),
                name = input.name.replaceIfNull(),
                air_date = input.airDate.replaceIfNull(),
                url = input.url.replaceIfNull(),
                episode = input.episode.replaceIfNull(),
                character = input.characters
            )
        }
    }
}
