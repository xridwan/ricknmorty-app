package com.eve.ricknmorty.ui.episode

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eve.data.utils.Constants.EXTRA_DATA
import com.eve.domain.Resource
import com.eve.domain.model.Episode
import com.eve.ricknmorty.base.BaseFragment
import com.eve.ricknmorty.databinding.FragmentEpisodeBinding
import com.eve.ricknmorty.ui.detailepisode.DetailEpisodeActivity
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
        getSearchEpisode()
        searchEpisode()
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

    private fun getSearchEpisode() {
        viewModel.searchEpisode.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    binding.rvEpisode.gone()
                    showShimmer(binding.shimmerEpisode.root)
                }
                is Resource.Success -> {
                    if (!response.data.isNullOrEmpty()) {
                        val data = response.data
                        dismissShimmer(binding.shimmerEpisode.root)
                        binding.rvEpisode.show()
                        episodeAdapter.differ.submitList(data)
                    }
                }
                is Resource.Error -> {
                    showToast(response.message.toString())
                    Log.d(TAG, "Search Error: ${response.message}")
                }
            }
        }
    }

    private fun searchEpisode() {
        binding.searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                showToast("Test")
                Log.d(TAG, "onClose: DI KLIK GES")
                return false
            }
        })
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.toString().trim() != "") {
                    viewModel.getSearchEpisode(query.toString())
                } else {
                    getAllEpisode()
                }
                return false
            }
        })
    }

    private fun setupRecyclerview() {
        binding.rvEpisode.apply {
            val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
            mLayoutManager.stackFromEnd = true
            layoutManager = mLayoutManager
            adapter = episodeAdapter
        }
    }

    override fun onClickListener(data: Episode) {
        showToast(data.name)
        val intent = Intent(context, DetailEpisodeActivity::class.java)
        intent.putExtra(EXTRA_DATA, data)
        startActivity(intent)
    }

    companion object {
        private val TAG = EpisodeFragment::class.java.simpleName
    }
}