package com.eve.ricknmorty

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.eve.domain.Resource
import com.eve.ricknmorty.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCharacter()
        getAllEpisode()
        getAllLocation()
        getCharacterDetail()
    }

    private fun getAllLocation() {
        viewModel.allLocation.observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    binding.tvLocation.text = getString(R.string.loading_text)
                }
                is Resource.Success -> {
                    val data = response.data?.size
                    binding.tvLocation.text = "Location : ${data.toString()}"
                }
                is Resource.Error -> {
                    binding.tvLocation.text = response.message
                }
            }
        }
    }

    private fun getAllEpisode() {
        viewModel.allEpisode.observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    Log.d("TAG", "getAllEpisode: Loading")
                }
                is Resource.Success -> {
                    Log.d("TAG", "getAllEpisode: ${response.data}")
                }
                is Resource.Error -> {
                    Log.d("TAG", "getAllEpisode: ${response.message}")
                }
            }
        }
    }

    private fun getCharacter() {
        viewModel.allCharacter.observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    binding.tvTest.text = getString(R.string.loading_text)
                }
                is Resource.Success -> {
                    val data = response.data?.size
                    binding.tvTest.text = "Character : ${data.toString()}"
                }
                is Resource.Error -> {
                    binding.tvTest.text = response.message
                }
            }
        }
    }

    private fun getCharacterDetail() {
        detailViewModel.getCharacter(2).observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    binding.tvCharacter.text = getString(R.string.loading_text)
                }
                is Resource.Success -> {
                    val data = response.data
                    binding.tvCharacter.text = data?.name
                }
                is Resource.Error -> {
                    binding.tvCharacter.text = response.message
                }
            }
        }
    }
}