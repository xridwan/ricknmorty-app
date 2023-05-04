package com.eve.ricknmorty.ui.detaillocation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.domain.model.Location
import com.eve.domain.usecase.location.LocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailLocationViewModel @Inject constructor(
    private val locationUseCase: LocationUseCase,
) : ViewModel() {

    fun getLocation(id: Int?) = locationUseCase.getLocation(id).asLiveData()

    val residentsList = MutableLiveData<MutableList<Character>>()
    private var list = mutableListOf<Character>()

    fun setResidents(data: Location?) {
        val list: MutableList<Character> = mutableListOf()
        data?.residents?.forEach {
            getResidentsItem(it, list)
            this.list = list
        }
    }

    fun getResidents() {
        residentsList.postValue(list)
    }

    private fun getResidentsItem(url: String, residentsList: MutableList<Character>) {
        viewModelScope.launch {
            locationUseCase.getCharacterItem(url).collectLatest { response ->
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val data = response.data
                        if (data != null) residentsList.add(data)
                    }
                    is Resource.Error -> {}
                }
            }
        }
    }

}