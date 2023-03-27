package com.eve.ricknmorty.utils

import android.content.Intent
import android.os.Build
import android.os.Parcelable

enum class MenuApp {
    CHARACTER,
    EPISODE,
    LOCATION
}

object Utils {
    inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
        Build.VERSION.SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
    }
}