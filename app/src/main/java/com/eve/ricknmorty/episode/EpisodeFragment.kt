package com.eve.ricknmorty.episode

import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.eve.domain.Resource
import com.eve.ricknmorty.base.BaseFragment
import com.eve.ricknmorty.databinding.FragmentEpisodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment : BaseFragment<FragmentEpisodeBinding>() {

    private val TAG = EpisodeFragment::class.java.simpleName

    private val viewModel: EpisodeViewModel by viewModels()

    override fun initializationLayout(inflater: LayoutInflater): FragmentEpisodeBinding {
        return FragmentEpisodeBinding.inflate(inflater)
    }

    override fun setupView() {
    }

    override fun initialization() {
        getAllEpisode()
    }

    private fun getAllEpisode() {
        viewModel.allEpisode.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    Log.d(TAG, "getAllEpisode: Loading...")
                }
                is Resource.Success -> {
                    Log.d(TAG, "getAllEpisode: ${response.data}")
                }
                is Resource.Error -> {

                }
            }
        }
    }

}