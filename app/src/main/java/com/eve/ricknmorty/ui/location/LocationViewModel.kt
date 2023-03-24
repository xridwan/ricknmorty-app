package com.eve.ricknmorty.ui.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.eve.domain.usecase.location.LocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    locationUseCase: LocationUseCase
): ViewModel() {

    val allLocation = locationUseCase.getAllLocation().asLiveData()
}