package com.eve.ricknmorty.ui.location

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eve.data.utils.Constants.EXTRA_DATA
import com.eve.domain.Resource
import com.eve.domain.model.Location
import com.eve.ricknmorty.base.BaseFragment
import com.eve.ricknmorty.databinding.FragmentLocationBinding
import com.eve.ricknmorty.ui.detaillocation.DetailLocationActivity
import com.eve.ricknmorty.utils.gone
import com.eve.ricknmorty.utils.show
import com.eve.ricknmorty.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : BaseFragment<FragmentLocationBinding>(), LocationAdapter.Listener {

    private val viewModel: LocationViewModel by viewModels()
    private val locationAdapter: LocationAdapter by lazy { LocationAdapter(this) }

    override fun initializationLayout(inflater: LayoutInflater): FragmentLocationBinding {
        return FragmentLocationBinding.inflate(inflater)
    }

    override fun setupView() {
        setupRecyclerview()
    }

    override fun initialization() {
        getAllLocation()
        getSearchLocation()
        searchLocation()
    }

    private fun getAllLocation() {
        viewModel.allLocation.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    showShimmer(binding.shimmerLocation.root)
                    Log.d(TAG, "getAllLocation: Loading...")
                }
                is Resource.Success -> {
                    if (!response.data.isNullOrEmpty()) {
                        dismissShimmer(binding.shimmerLocation.root)
                        binding.rvLocation.show()
                        locationAdapter.differ.submitList(response.data)
                    }
                }
                is Resource.Error -> {
                    showToast(response.message.toString())
                }
            }
        }
    }

    private fun getSearchLocation() {
        viewModel.searchLocation.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    binding.rvLocation.gone()
                    showShimmer(binding.shimmerLocation.root)
                }
                is Resource.Success -> {
                    if (!response.data.isNullOrEmpty()) {
                        val data = response.data
                        dismissShimmer(binding.shimmerLocation.root)
                        binding.rvLocation.show()
                        locationAdapter.differ.submitList(data)
                    }
                }
                is Resource.Error -> {
                    showToast(response.message.toString())
                }
            }
        }
    }

    private fun searchLocation() {
        binding.searchView.setOnCloseListener { false }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.toString().trim() != "") {
                    viewModel.getSearchLocation(query.toString())
                } else {
                    getAllLocation()
                }
                return false
            }
        })
    }

    override fun onClickListener(data: Location) {
        showToast(data.name)
        val intent = Intent(context, DetailLocationActivity::class.java)
        intent.putExtra(EXTRA_DATA, data)
        startActivity(intent)
    }

    private fun setupRecyclerview() {
        binding.rvLocation.apply {
            val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
            mLayoutManager.stackFromEnd = true
            layoutManager = mLayoutManager
            adapter = locationAdapter
        }
    }

    companion object {
        private val TAG = LocationFragment::class.java.simpleName
    }
}