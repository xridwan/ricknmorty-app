package com.eve.ricknmorty.ui.episode

import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eve.domain.Resource
import com.eve.ricknmorty.base.BaseFragment
import com.eve.ricknmorty.databinding.FragmentEpisodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment : BaseFragment<FragmentEpisodeBinding>() {

    private val TAG = EpisodeFragment::class.java.simpleName

    private val viewModel: EpisodeViewModel by viewModels()

    private val episodeAdapter: EpisodeAdapter by lazy { EpisodeAdapter() }

    override fun initializationLayout(inflater: LayoutInflater): FragmentEpisodeBinding {
        return FragmentEpisodeBinding.inflate(inflater)
    }

    override fun setupView() {
        setupRecyclerview()
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
                    if (!response.data.isNullOrEmpty()) {
                        episodeAdapter.differ.submitList(response.data)
                    }
                }
                is Resource.Error -> {

                }
            }
        }
    }

    private fun setupRecyclerview() {
        binding.rvEpisode.apply {
            val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
            mLayoutManager.stackFromEnd = true
            layoutManager = mLayoutManager
            adapter = episodeAdapter
        }
    }

}