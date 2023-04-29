package com.eve.ricknmorty.ui.character

import androidx.lifecycle.*
import com.eve.data.remote.response.CharacterResponse
import com.eve.domain.Resource
import com.eve.domain.usecase.character.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel  @Inject constructor(
    private val characterUseCase: CharacterUseCase
): ViewModel(){

    val allCharacter = characterUseCase.getAllCharacter().asLiveData()

    private val _searchCharacter = MutableLiveData<Resource<List<com.eve.domain.model.Character>>>()
    val searchCharacter: LiveData<Resource<List<com.eve.domain.model.Character>>> = _searchCharacter

    fun getSearchCharacter(name: String) = viewModelScope.launch {
        characterUseCase.getFilterCharacter(name)
            .collect {
                _searchCharacter.value = it
            }
    }
}