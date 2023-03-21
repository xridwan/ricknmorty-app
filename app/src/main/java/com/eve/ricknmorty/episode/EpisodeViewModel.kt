package com.eve.ricknmorty.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.eve.domain.usecase.episode.EpisodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    useCase: EpisodeUseCase,
) : ViewModel() {

    val allEpisode = useCase.getAllEpisode().asLiveData()

}