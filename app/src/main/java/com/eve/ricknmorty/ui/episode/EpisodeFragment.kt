package com.eve.ricknmorty.ui.episode

import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eve.domain.Resource
import com.eve.domain.model.Episode
import com.eve.ricknmorty.base.BaseFragment
import com.eve.ricknmorty.databinding.FragmentEpisodeBinding
import com.eve.ricknmorty.utils.gone
import com.eve.ricknmorty.utils.show
import com.eve.ricknmorty.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment : BaseFragment<FragmentEpisodeBinding>(), EpisodeAdapter.Listener {

    private val viewModel: EpisodeViewModel by viewModels()
    private val episodeAdapter: EpisodeAdapter by lazy { EpisodeAdapter(this) }

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
                    showShimmer(binding.shimmerEpisode.root)
                }
                is Resource.Success -> {
                    if (!response.data.isNullOrEmpty()) {
                        dismissShimmer(binding.shimmerEpisode.root)
                        binding.rvEpisode.show()
                        episodeAdapter.differ.submitList(response.data)
                    }
                }
                is Resource.Error -> {
                    showToast(response.message.toString())
                }
            }
        }
    }

    override fun listener(data: Episode) {
        showToast(data.name)
        Log.d(TAG, "listener: ${data.name}")
    }

    private fun setupRecyclerview() {
        binding.rvEpisode.apply {
            val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
            mLayoutManager.stackFromEnd = true
            layoutManager = mLayoutManager
            adapter = episodeAdapter
        }
    }

    companion object {
        private val TAG = EpisodeFragment::class.java.simpleName
    }
}