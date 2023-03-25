package com.eve.ricknmorty.ui.character

import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.eve.domain.Resource
import com.eve.ricknmorty.base.BaseFragment
import com.eve.ricknmorty.databinding.FragmentCharacterBinding
import com.eve.ricknmorty.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding>() {

    private val viewModel: CharacterViewModel by viewModels()
    private val characterAdapter: CharacterAdapter by lazy { CharacterAdapter() }

    override fun initializationLayout(inflater: LayoutInflater): FragmentCharacterBinding {
        return FragmentCharacterBinding.inflate(inflater)
    }

    override fun setupView() {
        setupRecyclerview()
    }

    override fun initialization() {
        getAllCharacter()
    }

    private fun getAllCharacter() {
        viewModel.allCharacter.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    Log.d(TAG, "getAllCharacter: Loading...")
                }
                is Resource.Success -> {
                    if (!response.data.isNullOrEmpty()) {
                        characterAdapter.differ.submitList(response.data)
                    }
                    Log.d(TAG, "getAllCharacter: ${response.data}")
                }
                is Resource.Error -> {
                    showToast(response.message.toString())
                }
            }
        }
    }

    private fun setupRecyclerview() {
        binding.rvCharacter.apply {
//            val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
//            val gridLayout = GridLayoutManager
//            mLayoutManager.stackFromEnd = true
//            layoutManager = mLayoutManager
            layoutManager = GridLayoutManager(context, 2)
            adapter = characterAdapter
        }
    }

    companion object {
        private val TAG = CharacterFragment::class.java.simpleName
    }
}