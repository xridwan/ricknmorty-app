package com.eve.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Episode(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val url: String,
    val created: String,
    val character: List<String>
) : Parcelable
