package com.eve.ricknmorty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.eve.domain.usecase.character.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    characterUseCase: CharacterUseCase,
) : ViewModel() {

    val allCharacter = characterUseCase.getAllCharacter().asLiveData()
}