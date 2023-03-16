package com.eve.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val image: String,
    val gender: String,
    val species: String,
    val created: String,
    val type: String,
    val url: String,
    val status: String,
    val name: String,
) : Parcelable
