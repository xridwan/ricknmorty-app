package com.eve.ricknmorty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.eve.domain.usecase.character.CharacterUseCase
import com.eve.domain.usecase.location.LocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase,
    private val locationUseCase: LocationUseCase,
) : ViewModel() {

    fun getCharacter(id: Int) = characterUseCase.getCharacter(id).asLiveData()

    fun getLocation(id: Int) = locationUseCase.getLocation(id).asLiveData()
}