package com.eve.ricknmorty.ui.detailcharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.eve.domain.usecase.character.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailCharacterViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase,
) : ViewModel() {

    fun getCharacter(id: Int?) = characterUseCase.getCharacter(id).asLiveData()

}