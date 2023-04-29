package com.eve.ricknmorty.ui.character

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.eve.data.utils.Constants.EXTRA_DATA
import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.ricknmorty.base.BaseFragment
import com.eve.ricknmorty.databinding.FragmentCharacterBinding
import com.eve.ricknmorty.ui.detailcharacter.DetailCharacterActivity
import com.eve.ricknmorty.utils.show
import com.eve.ricknmorty.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding>(), CharacterAdapter.Listener {

    private val viewModel: CharacterViewModel by viewModels()
    private val characterAdapter: CharacterAdapter by lazy { CharacterAdapter(this) }

    override fun initializationLayout(inflater: LayoutInflater): FragmentCharacterBinding {
        return FragmentCharacterBinding.inflate(inflater)
    }

    override fun setupView() {
        setupRecyclerview()
    }

    override fun initialization() {
        getAllCharacter()
        searchCharacter()
    }

    private fun getAllCharacter() {
        viewModel.allCharacter.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    showShimmer(binding.shimmerCharacter.root)
                }
                is Resource.Success -> {
                    if (!response.data.isNullOrEmpty()) {
                        dismissShimmer(binding.shimmerCharacter.root)
                        binding.rvCharacter.show()
                        characterAdapter.differ.submitList(response.data)
                    }
                }
                is Resource.Error -> {
                    showToast(response.message.toString())
                }
            }
        }

        viewModel.searchCharacter.observe(viewLifecycleOwner) { response ->
            when(response) {
                is Resource.Loading -> {
                    Log.d("TAG-SEARCH", "loading: ${response.data}")
                }
                is Resource.Success -> {
                    Log.d("TAG-SEARCH", "getAllCharacter: ${response.data}")
                    setupView()
                }
                is Resource.Error -> {
                    Log.d("TAG-SEARCH", "error: ${response.message}")
                }
            }
        }
    }

    private fun searchCharacter() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.toString().trim() != "") {
                    try {
                        viewModel.getSearchCharacter(newText.toString())
                    } catch (e: Exception) {
                        e.message
                    }
                }
                return false
            }
        })
    }

    private fun setupRecyclerview() {
        binding.rvCharacter.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = characterAdapter
        }
    }


    override fun onClickListener(data: Character) {
        val intent = Intent(context, DetailCharacterActivity::class.java)
        intent.putExtra(EXTRA_DATA, data)
        startActivity(intent)
    }

    companion object {
        private val TAG = CharacterFragment::class.java.simpleName
    }

}