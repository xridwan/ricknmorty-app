package com.eve.ricknmorty.ui.detailepisode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.domain.model.Episode
import com.eve.domain.usecase.episode.EpisodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailEpisodeViewModel @Inject constructor(
    private val episodeUseCase: EpisodeUseCase,
) : ViewModel() {

    fun getEpisode(id: Int?) = episodeUseCase.getEpisode(id).asLiveData()


    val characterList = MutableLiveData<MutableList<Character>>()
    private var list = mutableListOf<Character>()

    fun setEpisode(data: Episode?) {
        val list: MutableList<Character> = mutableListOf()
        data?.character?.forEach {
            getCharacterItem(it, list)
            this.list = list
        }
    }

    fun getCharacter() {
        characterList.postValue(list)
    }

    private fun getCharacterItem(url: String?, characterList: MutableList<Character>) {
        viewModelScope.launch {
            episodeUseCase.getCharacterItem(url).collectLatest { response ->
                when (response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val data = response.data
                        if (data != null) characterList.add(data)
                    }
                    is Resource.Error -> {}
                }
            }
        }
    }

}