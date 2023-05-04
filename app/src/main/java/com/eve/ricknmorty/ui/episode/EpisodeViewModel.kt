package com.eve.ricknmorty.ui.episode

import androidx.lifecycle.*
import com.eve.domain.Resource
import com.eve.domain.model.Episode
import com.eve.domain.usecase.episode.EpisodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val episodeUseCase: EpisodeUseCase,
) : ViewModel() {

    val allEpisode = episodeUseCase.getAllEpisode().asLiveData()

    private val _searchEpisode = MutableLiveData<Resource<List<Episode>>>()
    val searchEpisode: LiveData<Resource<List<Episode>>> = _searchEpisode

    fun getSearchEpisode(name: String) = viewModelScope.launch {
        episodeUseCase.getFilterEpisode(name).collect {
                _searchEpisode.value = it
            }
    }

}