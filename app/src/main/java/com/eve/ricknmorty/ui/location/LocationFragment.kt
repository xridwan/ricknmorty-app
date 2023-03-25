package com.eve.ricknmorty.ui.location

import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eve.domain.Resource
import com.eve.domain.model.Location
import com.eve.ricknmorty.base.BaseFragment
import com.eve.ricknmorty.databinding.FragmentLocationBinding
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
    }

    private fun getAllLocation() {
        viewModel.allLocation.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    Log.d(TAG, "getAllLocation: Loading...")
                }
                is Resource.Success -> {
                    if (!response.data.isNullOrEmpty()) {
                        locationAdapter.differ.submitList(response.data)
                    }
                }
                is Resource.Error -> {
                    showToast(response.message.toString())
                }
            }
        }
    }

    override fun listener(data: Location) {
        showToast(data.name)
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