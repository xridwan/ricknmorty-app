package com.eve.ricknmorty

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.domain.model.Episode
import com.eve.domain.usecase.character.CharacterUseCase
import com.eve.domain.usecase.location.LocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase,
    private val locationUseCase: LocationUseCase,
) : ViewModel() {

    fun getCharacter(id: Int) = characterUseCase.getCharacter(id).asLiveData()

    fun getLocation(id: Int) = locationUseCase.getLocation(id).asLiveData()
}