package com.eve.ricknmorty.ui.character

import androidx.lifecycle.*
import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.domain.usecase.character.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase,
) : ViewModel() {

    val allCharacter = characterUseCase.getAllCharacter().asLiveData()

    private val _searchCharacter = MutableLiveData<Resource<List<Character>>>()
    val searchCharacter: LiveData<Resource<List<Character>>> = _searchCharacter

    fun getSearchCharacter(name: String) = viewModelScope.launch {
        characterUseCase.getFilterCharacter(name)
            .collect {
                _searchCharacter.value = it
            }
    }
}