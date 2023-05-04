package com.eve.data.remote.response

import android.os.Parcelable
import com.eve.data.local.entity.LocationEntity
import com.eve.data.utils.replaceIfNull
import com.eve.domain.model.Location
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
    val created: String?,

    @field:SerializedName("name")
    val name: String?,

//	@field:SerializedName("residents")
//	val residents: List<String>,

    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("type")
    val type: String?,

    @field:SerializedName("dimension")
    val dimension: String?,

    @field:SerializedName("url")
    val url: String?,

    val residents: List<String>,
) : Parcelable {
    companion object {
        fun transformToEntities(input: LocationResponse): List<LocationEntity> {
            val dataList = ArrayList<LocationEntity>()
            input.results.map {
                val item = LocationEntity(
                    id = it.id.replaceIfNull(),
                    created = it.created.replaceIfNull(),
                    name = it.name.replaceIfNull(),
                    type = it.type.replaceIfNull(),
                    dimension = it.dimension.replaceIfNull(),
                    url = it.url.replaceIfNull(),
                )
                dataList.add(item)
            }
            return dataList
        }

        fun transformFilterToDomain(input: LocationResponse): List<Location> {
            val dataList = ArrayList<Location>()
            input.results.map {
                val item = Location(
                    id = it.id.replaceIfNull(),
                    created = it.created.replaceIfNull(),
                    name = it.name.replaceIfNull(),
                    type = it.type.replaceIfNull(),
                    dimension = it.dimension.replaceIfNull(),
                    url = it.url.replaceIfNull(),
                    residents = it.residents
                )
                dataList.add(item)
            }
            return dataList
        }

        fun transformDetailToDomain(input: LocationItem): Location {
            return Location(
                id = input.id.replaceIfNull(),
                created = input.created.replaceIfNull(),
                name = input.name.replaceIfNull(),
                type = input.type.replaceIfNull(),
                dimension = input.dimension.replaceIfNull(),
                url = input.url.replaceIfNull(),
                residents = input.residents
            )
        }
    }
}
