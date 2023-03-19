package com.eve.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val id: Int,
    val created: String,
    val name: String,
    val type: String,
    val dimension: String,
    val url: String,
) : Parcelable