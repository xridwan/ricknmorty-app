package com.eve.ricknmorty.ui.detailcharacter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.domain.model.Episode
import com.eve.domain.usecase.character.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailCharacterViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase,
) : ViewModel() {

    fun getCharacter(id: Int?) = characterUseCase.getCharacter(id).asLiveData()

    /* not best practice */
    val episodeList = MutableLiveData<MutableList<Episode>>()
    private var list: MutableList<Episode> = mutableListOf()

    fun setCharacter(data: Character?) {
        val list: MutableList<Episode> = mutableListOf()
        data?.episode?.forEach {
            getEpisodeItem(it, list)
            this.list = list
        }
    }

    fun getEpisode() {
        episodeList.postValue(list)
    }

    private fun getEpisodeItem(url: String, episodeList: MutableList<Episode>) {
        viewModelScope.launch {
            characterUseCase.getEpisodeItem(url).collectLatest { response ->
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val data = response.data
                        if (data != null) episodeList.add(data)
                    }
                    is Resource.Error -> {}
                }
            }
        }
    }
}