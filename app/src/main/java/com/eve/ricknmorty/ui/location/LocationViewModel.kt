package com.eve.ricknmorty.ui.location

import androidx.lifecycle.*
import com.eve.domain.Resource
import com.eve.domain.model.Location
import com.eve.domain.usecase.location.LocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationUseCase: LocationUseCase,
) : ViewModel() {

    val allLocation = locationUseCase.getAllLocation().asLiveData()

    private val _searchLocation = MutableLiveData<Resource<List<Location>>>()
    val searchLocation: LiveData<Resource<List<Location>>> = _searchLocation

    fun getSearchLocation(name: String) = viewModelScope.launch {
        locationUseCase.getFilterLocation(name).collect {
                _searchLocation.value = it
            }
    }

}