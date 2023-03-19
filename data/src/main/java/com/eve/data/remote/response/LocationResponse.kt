package com.eve.data.remote.response

import android.os.Parcelable
import com.eve.data.local.entity.LocationEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationResponse(

    @field:SerializedName("results")
    val results: List<LocationItem>,

//	@field:SerializedName("info")
//	val info: Info
) : Parcelable

//data class Info(
//
//	@field:SerializedName("next")
//	val next: String,
//
//	@field:SerializedName("pages")
//	val pages: Int,
//
//	@field:SerializedName("prev")
//	val prev: Any,
//
//	@field:SerializedName("count")
//	val count: Int
//)

@Parcelize
data class LocationItem(

    @field:SerializedName("created")
    val created: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

//	@field:SerializedName("residents")
//	val residents: List<String>,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("dimension")
    val dimension: String? = null,

    @field:SerializedName("url")
    val url: String? = null,
) : Parcelable {
    companion object {
        fun transformToEntities(input: LocationResponse): List<LocationEntity> {
            val dataList = ArrayList<LocationEntity>()
            input.results.map {
                val item = LocationEntity(
                    id = it.id ?: 0,
                    created = it.created ?: "",
                    name = it.name ?: "",
                    type = it.type ?: "",
                    dimension = it.dimension ?: "",
                    url = it.url ?: "",
                )
                dataList.add(item)
            }
            return dataList
        }
    }
}
