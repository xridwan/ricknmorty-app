package com.eve.data.remote.response

import android.os.Parcelable
import com.eve.data.local.entity.EpisodeEntity
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
    val airDate: String? = null,

    @field:SerializedName("characters")
    val characters: List<String?>? = null,

    @field:SerializedName("created")
    val created: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("episode")
    val episode: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("url")
    val url: String? = null,
) : Parcelable {
    companion object {
        fun transformToEntities(input: EpisodeResponse): List<EpisodeEntity> {
            val dataList = ArrayList<EpisodeEntity>()
            input.results.map {
                val item = EpisodeEntity(
                    id = it.id ?: 0,
                    name = it.name ?: "",
                    air_date = it.airDate ?: "",
                    episode = it.episode ?: "",
                    url = it.url ?: "",
                    created = it.created ?: ""
                )
                dataList.add(item)
            }
            return dataList
        }
    }
}
