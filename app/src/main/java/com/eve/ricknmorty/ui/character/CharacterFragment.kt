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
import com.eve.ricknmorty.utils.gone
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
        getSearchCharacter()
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
    }

    private fun getSearchCharacter() {
        viewModel.searchCharacter.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    binding.rvCharacter.gone()
                    showShimmer(binding.shimmerCharacter.root)
                    showToast("Loading ...")
                }
                is Resource.Success -> {
                    val data = response.data
                    dismissShimmer(binding.shimmerCharacter.root)
                    binding.rvCharacter.show()
                    characterAdapter.differ.submitList(data)
                }
                is Resource.Error -> {
                    showToast(response.message.toString())
                    Log.d(TAG, "Search Error: ${response.message}")
                }
            }
        }
    }

    private fun searchCharacter() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.toString().trim() != "") {
                    viewModel.getSearchCharacter(query.toString())
                } else {
                    getAllCharacter()
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