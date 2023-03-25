package com.eve.ricknmorty.utils

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}